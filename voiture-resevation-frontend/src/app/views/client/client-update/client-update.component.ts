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


import {ClientService} from "src/app/controller/services/client.service";
import {Client} from "src/app/controller/entities/client";
import {ClientValidator} from "src/app/controller/validators/client.validator";

@Component({
  selector: 'app-client-update',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent, NgTemplateOutlet,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, FormCheckComponent, SpinnerComponent,
    FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent, IconDirective,

  ],
  templateUrl: './client-update.component.html',
  styleUrl: './client-update.component.scss'
})
export class ClientUpdateComponent {
  protected isPartOfUpdateForm = false // true if it is used as part of other update component
  protected sending = false
  protected resetting = false
  protected standAlon = true

  @Input("getter") set setItemGetter(getter: () => Client) {
    this.itemGetter = getter
    this.standAlon = false
    this.isPartOfUpdateForm = true
  }

  @Input("setter") set setItemSetter(setter: (value: Client | undefined) => void) {
    this.itemSetter = setter
  }

  @Input("validator") set setValidator(validator: ClientValidator) {
    this.validator = validator
  }

  private router = inject(Router)
  private service = inject(ClientService)

  protected validator = ClientValidator.init(() => this.item)


  ngAfterContentInit() {
    if (!this.isPartOfUpdateForm && this.item.id == null) this.router.navigate(["/client"]).then()
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
        this.router.navigate(["/client"]).then()
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

  public get item(): Client {
    return this.itemGetter();
  }

  public set item(value: Client | undefined) {
    this.itemSetter(value);
  }

  private itemGetter(): Client {
    return this.service.item;
  }

  private itemSetter(value: Client | undefined) {
    this.service.item = value;
  }


  ////
}
