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


import {ReservationService} from "src/app/controller/services/reservation.service";
import {Reservation} from "src/app/controller/entities/reservation";
import {ReservationValidator} from "src/app/controller/validators/reservation.validator";

@Component({
  selector: 'app-reservation-create',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective, NgTemplateOutlet,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, SpinnerComponent, IconDirective,
    FormCheckComponent, FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent,

  ],
  templateUrl: './reservation-create.component.html',
  styleUrl: './reservation-create.component.scss'
})
export class ReservationCreateComponent {
  protected sending = false
  protected standAlon = true

  @Input("getter") set setItemGetter(getter: () => Reservation) {
    this.itemGetter = getter
    this.standAlon = false
  }

  @Input("setter") set setItemSetter(setter: (value: Reservation | undefined) => void) {
    this.itemSetter = setter
  }

  @Input("validator") set setValidator(validator: ReservationValidator) {
    this.validator = validator
  }

  private router = inject(Router)
  private service = inject(ReservationService)

  protected validator = ReservationValidator.init(() => this.item)


  ngOnInit() {
    if (this.service.keepData) {
    } else {
      this.reset(false)
    }
    this.service.keepData = false

  }

  // LOAD DATA

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
        this.router.navigate(["/reservation"]).then()
      },
      error: err => {
        this.sending = false
        console.log(err)
      }
    })
  }

  reset(force = true) {
    if (force || this.item == null) this.item = new Reservation()
    this.validator.reset()
  }

  // GETTERS AND SETTERS
  public get items() {
    return this.service.items;
  }

  public set items(value) {
    this.service.items = value;
  }

  public get item(): Reservation {
    return this.itemGetter();
  }

  public set item(value: Reservation | undefined) {
    this.itemSetter(value);
  }

  private itemGetter(): Reservation {
    return this.service.item;
  }

  private itemSetter(value: Reservation | undefined) {
    this.service.item = value;
  }


  public get returnUrl() {
    return this.service.returnUrl;
  }

  public get toReturn() {
    return this.service.toReturn();
  }


  ////
}
