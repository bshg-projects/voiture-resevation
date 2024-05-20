import {Component, inject, Input} from '@angular/core';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  FormCheckComponent,
  FormCheckInputDirective,
  FormCheckLabelDirective,
  FormControlDirective,
  FormFeedbackComponent,
  FormFloatingDirective,
  FormLabelDirective,
  FormSelectDirective,
  InputGroupComponent,
  NavComponent,
  NavItemComponent,
  RowComponent,
  SpinnerComponent
} from "@coreui/angular";
import {FormsModule} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {IconDirective} from "@coreui/icons-angular";
import {NgTemplateOutlet} from "@angular/common";


import {AdministrateurService} from "src/app/controller/services/administrateur.service";
import {Administrateur} from "src/app/controller/entities/administrateur";
import {AdministrateurValidator} from "src/app/controller/validators/administrateur.validator";
import {ReservationService} from "src/app/controller/services/reservation.service";
import {Reservation} from "src/app/controller/entities/reservation";
import {OffreService} from "src/app/controller/services/offre.service";
import {Offre} from "src/app/controller/entities/offre";

@Component({
  selector: 'app-administrateur-create',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective, NgTemplateOutlet,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, SpinnerComponent, IconDirective,
    FormCheckComponent, FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent,

  ],
  templateUrl: './administrateur-create.component.html',
  styleUrl: './administrateur-create.component.scss'
})
export class AdministrateurCreateComponent {
  protected sending = false
  protected standAlon = true

  @Input("getter") set setItemGetter(getter: () => Administrateur) {
    this.itemGetter = getter
    this.standAlon = false
  }

  @Input("setter") set setItemSetter(setter: (value: Administrateur | undefined) => void) {
    this.itemSetter = setter
  }

  @Input("validator") set setValidator(validator: AdministrateurValidator) {
    this.validator = validator
  }

  private router = inject(Router)
  private service = inject(AdministrateurService)
  private reservationService = inject(ReservationService)
  private offreService = inject(OffreService)

  protected validator = AdministrateurValidator.init(() => this.item)

  protected reservationList!: Reservation[]
  protected offreList!: Offre[]

  ngOnInit() {
    if (this.service.keepData) {
      let reservationsCreated = this.reservationService.createdItemAfterReturn;
      if (reservationsCreated.created) {
        this.item.reservations = reservationsCreated.item
        this.validator.reservations.validate()
      }
      let offresCreated = this.offreService.createdItemAfterReturn;
      if (offresCreated.created) {
        this.item.offres = offresCreated.item
        this.validator.offres.validate()
      }
    } else {
      this.reset(false)
    }
    this.service.keepData = false

    this.loadReservationList()
    this.loadOffreList()
  }

  // LOAD DATA
  loadReservationList() {
    this.reservationService.findAllOptimized().subscribe({
      next: data => this.reservationList = data,
      error: err => console.log(err)
    })
  }

  loadOffreList() {
    this.offreService.findAllOptimized().subscribe({
      next: data => this.offreList = data,
      error: err => console.log(err)
    })
  }

  // METHODS
  create() {
    console.log(this.item)
    if (!this.validator.validate()) return;
    this.sending = true;
    this.service.create().subscribe({
      next: data => {
        this.sending = false
        if (data == null) return
        if (this.toReturn) {
          this.item = data
          this.router.navigate([this.returnUrl]).then()
          return;
        }
        this.item = undefined
        this.router.navigate(["/administrateur"]).then()
      },
      error: err => {
        this.sending = false
        console.log(err)
      }
    })
  }

  reset(force = true) {
    if (force || this.item == null) this.item = new Administrateur()
    this.validator.reset()
  }

  // GETTERS AND SETTERS
  public get items() {
    return this.service.items;
  }

  public set items(value) {
    this.service.items = value;
  }

  public get item(): Administrateur {
    return this.itemGetter();
  }

  public set item(value: Administrateur | undefined) {
    this.itemSetter(value);
  }

  private itemGetter(): Administrateur {
    return this.service.item;
  }

  private itemSetter(value: Administrateur | undefined) {
    this.service.item = value;
  }

  public get reservations(): Reservation {
    if (this.item.reservations == null)
      this.item.reservations = new Reservation()
    return this.item.reservations;
  }

  public set reservations(value: Reservation | undefined) {
    this.item.reservations = value;
  }


  public get offres(): Offre {
    if (this.item.offres == null)
      this.item.offres = new Offre()
    return this.item.offres;
  }

  public set offres(value: Offre | undefined) {
    this.item.offres = value;
  }


  public get returnUrl() {
    return this.service.returnUrl;
  }

  public get toReturn() {
    return this.service.toReturn();
  }

  public navigateToReservationCreate() {
    this.reservationService.returnUrl = this.router.url
    this.service.keepData = true
    this.router.navigate(['/reservation/create']).then()
  }

  public navigateToOffreCreate() {
    this.offreService.returnUrl = this.router.url
    this.service.keepData = true
    this.router.navigate(['/offre/create']).then()
  }

  ////
}
