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
  selector: 'app-administrateur-update',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent, NgTemplateOutlet,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, FormCheckComponent, SpinnerComponent,
    FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent, IconDirective,

  ],
  templateUrl: './administrateur-update.component.html',
  styleUrl: './administrateur-update.component.scss'
})
export class AdministrateurUpdateComponent {
  protected isPartOfUpdateForm = false // true if it is used as part of other update component
  protected sending = false
  protected resetting = false
  protected standAlon = true

  @Input("getter") set setItemGetter(getter: () => Administrateur) {
    this.itemGetter = getter
    this.standAlon = false
    this.isPartOfUpdateForm = true
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

  ngAfterContentInit() {
    if (!this.isPartOfUpdateForm && this.item.id == null) this.router.navigate(["/administrateur"]).then()
  }

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
      this.validator.reset()
    }

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
  update() {
    console.log(this.item)
    if (!this.validator.validate()) return;
    this.sending = true;
    this.service.update().subscribe({
      next: data => {
        this.sending = false
        if (data == null) return
        this.router.navigate(["/administrateur"]).then()
      },
      error: err => {
        this.sending = false
        console.log(err)
      }
    })
  }

  reset() {
    this.resetting = true
    this.service.findById(this.item.id).subscribe({
      next: value => {
        this.item = value
        this.validator.reset()
        this.resetting = false
      },
      error: err => {
        console.log(err)
        this.resetting = false
      }
    })
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


  public navigateToReservationCreate() {
    this.reservationService.returnUrl = this.router.url
    this.router.navigate(['/reservation/create']).then()
  }

  public navigateToOffreCreate() {
    this.offreService.returnUrl = this.router.url
    this.router.navigate(['/offre/create']).then()
  }

  ////
}
