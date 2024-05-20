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


import {OffreService} from "src/app/controller/services/offre.service";
import {Offre} from "src/app/controller/entities/offre";
import {OffreValidator} from "src/app/controller/validators/offre.validator";

@Component({
  selector: 'app-offre-create',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective, NgTemplateOutlet,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, SpinnerComponent, IconDirective,
    FormCheckComponent, FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent,

  ],
  templateUrl: './offre-create.component.html',
  styleUrl: './offre-create.component.scss'
})
export class OffreCreateComponent {
  protected sending = false
  protected standAlon = true

  @Input("getter") set setItemGetter(getter: () => Offre) {
    this.itemGetter = getter
    this.standAlon = false
  }

  @Input("setter") set setItemSetter(setter: (value: Offre | undefined) => void) {
    this.itemSetter = setter
  }

  @Input("validator") set setValidator(validator: OffreValidator) {
    this.validator = validator
  }

  private router = inject(Router)
  private service = inject(OffreService)

  protected validator = OffreValidator.init(() => this.item)


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
        this.router.navigate(["/offre"]).then()
      },
      error: err => {
        this.sending = false
        console.log(err)
      }
    })
  }

  reset(force = true) {
    if (force || this.item == null) this.item = new Offre()
    this.validator.reset()
  }

  // GETTERS AND SETTERS
  public get items() {
    return this.service.items;
  }

  public set items(value) {
    this.service.items = value;
  }

  public get item(): Offre {
    return this.itemGetter();
  }

  public set item(value: Offre | undefined) {
    this.itemSetter(value);
  }

  private itemGetter(): Offre {
    return this.service.item;
  }

  private itemSetter(value: Offre | undefined) {
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
