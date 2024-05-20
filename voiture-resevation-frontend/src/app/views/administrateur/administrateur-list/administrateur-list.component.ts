import {Component, inject} from '@angular/core';
import {
  ButtonCloseDirective,
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  ColComponent,
  ColDirective,
  DropdownComponent,
  DropdownHeaderDirective,
  DropdownItemDirective,
  DropdownMenuDirective,
  DropdownToggleDirective,
  ModalBodyComponent,
  ModalComponent,
  ModalFooterComponent,
  ModalHeaderComponent,
  ModalTitleDirective,
  ModalToggleDirective,
  NavComponent,
  NavItemComponent,
  PageItemDirective,
  PageLinkDirective,
  PaginationComponent,
  PlaceholderAnimationDirective,
  PlaceholderDirective,
  PopoverDirective,
  RowComponent,
  SpinnerComponent,
  TableDirective,
  TooltipDirective,
} from "@coreui/angular";
import {AdministrateurService} from "src/app/controller/services/administrateur.service";
import {Administrateur} from "src/app/controller/entities/administrateur";
import {RouterLink} from "@angular/router";
import {IconDirective} from "@coreui/icons-angular";
import {generatePageNumbers, paginationSizes} from "src/app/controller/utils/pagination/pagination";

@Component({
  selector: 'app-administrateur-list',
  standalone: true,
  imports: [
    RowComponent, ColComponent, CardComponent, CardBodyComponent, TableDirective,
    ButtonDirective, RouterLink, IconDirective, IconDirective, NavComponent,
    NavItemComponent, SpinnerComponent, PlaceholderAnimationDirective, PlaceholderDirective,
    ColDirective, PageItemDirective, PageLinkDirective, PaginationComponent,
    DropdownComponent, DropdownToggleDirective, DropdownMenuDirective, DropdownHeaderDirective, DropdownItemDirective,
    ModalComponent, ModalToggleDirective, ModalHeaderComponent, ModalBodyComponent, TooltipDirective, ModalFooterComponent, PopoverDirective, ModalTitleDirective, ButtonCloseDirective,
  ],
  templateUrl: './administrateur-list.Component.html',
  styleUrl: './administrateur-list.Component.scss'
})
export class AdministrateurListComponent {
  protected loading = false
  protected paginating = false
  protected currentIndex: number | undefined = undefined
  protected deleteModel = false

  private service = inject(AdministrateurService)

  ngOnInit() {
    this.findAll()
  }

  findAll() {
    this.loading = true
    this.paginate().then(() => this.loading = false)
  }

  async paginate(page: number = this.pagination.page, size: number = this.pagination.size) {
    this.paginating = true
    this.service.findPaginated(page, size).subscribe({
      next: value => {
        this.pagination = value
        this.paginating = false
      },
      error: err => {
        console.log(err)
        this.paginating = false
      }
    })
  }

  delete() {
    this.service.deleteById(this.item.id).subscribe({
      next: value => {
        this.pagination.data.splice(this.currentIndex as number, 1)
        this.pagination.totalElements--
        this.item = undefined
        this.currentIndex = undefined
        this.deleteModel = false
      },
      error: err => {
        console.log(err)
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

  public get pagination() {
    return this.service.pagination;
  }

  public set pagination(value) {
    this.service.pagination = value;
  }

  public get item(): Administrateur {
    return this.service.item;
  }

  public set item(value: Administrateur | undefined) {
    this.service.item = value;
  }

  public get generatePageNumbers() {
    return generatePageNumbers(this.pagination)
  }

  /////
  protected readonly paginationSizes = paginationSizes;
}
