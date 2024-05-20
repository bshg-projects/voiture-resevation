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


import {VoitureService} from "src/app/controller/services/voiture.service";
import {Voiture} from "src/app/controller/entities/voiture";
import {VoitureValidator} from "src/app/controller/validators/voiture.validator";

@Component({
  selector: 'app-voiture-update',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent, NgTemplateOutlet,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, FormCheckComponent, SpinnerComponent,
    FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent, IconDirective,

  ],
  templateUrl: './voiture-update.component.html',
  styleUrl: './voiture-update.component.scss'
})
export class VoitureUpdateComponent {
  protected isPartOfUpdateForm = false // true if it is used as part of other update component
  protected sending = false
  protected resetting = false
  protected standAlon = true

  @Input("getter") set setItemGetter(getter: () => Voiture) {
    this.itemGetter = getter
    this.standAlon = false
    this.isPartOfUpdateForm = true
  }

  @Input("setter") set setItemSetter(setter: (value: Voiture | undefined) => void) {
    this.itemSetter = setter
  }

  @Input("validator") set setValidator(validator: VoitureValidator) {
    this.validator = validator
  }

  private router = inject(Router)
  private service = inject(VoitureService)

  protected validator = VoitureValidator.init(() => this.item)


  ngAfterContentInit() {
    if (!this.isPartOfUpdateForm && this.item.id == null) this.router.navigate(["/voiture"]).then()
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
        this.router.navigate(["/voiture"]).then()
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

  public get item(): Voiture {
    return this.itemGetter();
  }

  public set item(value: Voiture | undefined) {
    this.itemSetter(value);
  }

  private itemGetter(): Voiture {
    return this.service.item;
  }

  private itemSetter(value: Voiture | undefined) {
    this.service.item = value;
  }


  ////
}
