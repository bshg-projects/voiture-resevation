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
  selector: 'app-offre-update',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent, NgTemplateOutlet,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, FormCheckComponent, SpinnerComponent,
    FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent, IconDirective,

  ],
  templateUrl: './offre-update.component.html',
  styleUrl: './offre-update.component.scss'
})
export class OffreUpdateComponent {
  protected isPartOfUpdateForm = false // true if it is used as part of other update component
  protected sending = false
  protected resetting = false
  protected standAlon = true

  @Input("getter") set setItemGetter(getter: () => Offre) {
    this.itemGetter = getter
    this.standAlon = false
    this.isPartOfUpdateForm = true
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


  ngAfterContentInit() {
    if (!this.isPartOfUpdateForm && this.item.id == null) this.router.navigate(["/offre"]).then()
  }

  ngOnInit() {
    if (this.service.keepData) {
    } else {
      this.validator.reset()
    }

  }

  // LOAD DATA

  // METHODS
  update() {
    console.log(this.item)
    if (!this.validator.validate()) return;
    this.sending = true;
    this.service.update().subscribe({
      next: data => {
        this.sending = false
        if (data == null) return
        this.router.navigate(["/offre"]).then()
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


  ////
}
