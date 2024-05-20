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


import {ContratService} from "src/app/controller/services/contrat.service";
import {Contrat} from "src/app/controller/entities/contrat";
import {ContratValidator} from "src/app/controller/validators/contrat.validator";
import {AdministrateurService} from "src/app/controller/services/administrateur.service";
import {Administrateur} from "src/app/controller/entities/administrateur";
import {ClientService} from "src/app/controller/services/client.service";
import {Client} from "src/app/controller/entities/client";

@Component({
  selector: 'app-contrat-create',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective, NgTemplateOutlet,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, SpinnerComponent, IconDirective,
    FormCheckComponent, FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent,

  ],
  templateUrl: './contrat-create.component.html',
  styleUrl: './contrat-create.component.scss'
})
export class ContratCreateComponent {
  protected sending = false
  protected standAlon = true

  @Input("getter") set setItemGetter(getter: () => Contrat) {
    this.itemGetter = getter
    this.standAlon = false
  }

  @Input("setter") set setItemSetter(setter: (value: Contrat | undefined) => void) {
    this.itemSetter = setter
  }

  @Input("validator") set setValidator(validator: ContratValidator) {
    this.validator = validator
  }

  private router = inject(Router)
  private service = inject(ContratService)
  private administrateurService = inject(AdministrateurService)
  private clientService = inject(ClientService)

  protected validator = ContratValidator.init(() => this.item)

  protected administrateurList!: Administrateur[]
  protected clientList!: Client[]

  ngOnInit() {
    if (this.service.keepData) {
      let administrateurCreated = this.administrateurService.createdItemAfterReturn;
      if (administrateurCreated.created) {
        this.item.administrateur = administrateurCreated.item
        this.validator.administrateur.validate()
      }
      let clientCreated = this.clientService.createdItemAfterReturn;
      if (clientCreated.created) {
        this.item.client = clientCreated.item
        this.validator.client.validate()
      }
    } else {
      this.reset(false)
    }
    this.service.keepData = false

    this.loadAdministrateurList()
    this.loadClientList()
  }

  // LOAD DATA
  loadAdministrateurList() {
    this.administrateurService.findAllOptimized().subscribe({
      next: data => this.administrateurList = data,
      error: err => console.log(err)
    })
  }

  loadClientList() {
    this.clientService.findAllOptimized().subscribe({
      next: data => this.clientList = data,
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
        this.router.navigate(["/contrat"]).then()
      },
      error: err => {
        this.sending = false
        console.log(err)
      }
    })
  }

  reset(force = true) {
    if (force || this.item == null) this.item = new Contrat()
    this.validator.reset()
  }

  // GETTERS AND SETTERS
  public get items() {
    return this.service.items;
  }

  public set items(value) {
    this.service.items = value;
  }

  public get item(): Contrat {
    return this.itemGetter();
  }

  public set item(value: Contrat | undefined) {
    this.itemSetter(value);
  }

  private itemGetter(): Contrat {
    return this.service.item;
  }

  private itemSetter(value: Contrat | undefined) {
    this.service.item = value;
  }

  public get administrateur(): Administrateur {
    if (this.item.administrateur == null)
      this.item.administrateur = new Administrateur()
    return this.item.administrateur;
  }

  public set administrateur(value: Administrateur | undefined) {
    this.item.administrateur = value;
  }


  public get client(): Client {
    if (this.item.client == null)
      this.item.client = new Client()
    return this.item.client;
  }

  public set client(value: Client | undefined) {
    this.item.client = value;
  }


  public get returnUrl() {
    return this.service.returnUrl;
  }

  public get toReturn() {
    return this.service.toReturn();
  }

  public navigateToAdministrateurCreate() {
    this.administrateurService.returnUrl = this.router.url
    this.service.keepData = true
    this.router.navigate(['/administrateur/create']).then()
  }

  public navigateToClientCreate() {
    this.clientService.returnUrl = this.router.url
    this.service.keepData = true
    this.router.navigate(['/client/create']).then()
  }

  ////
}
