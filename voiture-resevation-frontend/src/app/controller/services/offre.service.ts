import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from 'src/environments/environment';
import {Pagination} from "src/app/controller/utils/pagination/pagination";
import {Offre} from 'src/app/controller/entities/offre';

@Injectable({providedIn: 'root'})
export class OffreService {
  private readonly api = environment.apiUrl + "offre";
  private _item!: Offre | undefined;
  private _items!: Array<Offre>;
  private _pagination!: Pagination<Offre>

  private http = inject(HttpClient)

  public keepData: boolean = false
  public returnUrl: string | undefined = undefined
  public toReturn = () => this.returnUrl != undefined


  public findAll() {
    return this.http.get<Array<Offre>>(this.api);
  }

  public findById(id: number) {
    return this.http.get<Offre>(`${this.api}/id/${id}`);
  }

  public findAllOptimized() {
    return this.http.get<Array<Offre>>(`${this.api}/optimized`);
  }

  public findPaginated(page: number = 0, size: number = 10) {
    return this.http.get<Pagination<Offre>>(`${this.api}/paginated?page=${page}&size=${size}`);
  }

  public create() {
    return this.http.post<Offre>(this.api, this.item);
  }

  public createList() {
    return this.http.post<Array<Offre>>(`${this.api}/all`, this.items);
  }

  public update() {
    return this.http.put<Offre>(this.api, this.item);
  }

  public updateList() {
    return this.http.put<Array<Offre>>(`${this.api}/all`, this.items);
  }

  public delete(dto: Offre) {
    return this.http.delete<number>(this.api, {body: dto});
  }

  public deleteAll(dtos: Array<Offre>) {
    return this.http.delete<number>(this.api, {body: dtos});
  }

  public deleteById(id: number) {
    return this.http.delete<number>(`${this.api}/id/${id}`);
  }


  //------------- getters and setters -----------------------
  public get itemIsNull(): boolean {
    return this._item == undefined
  }

  public get items() {
    if (this._items == undefined)
      this._items = [];
    return this._items;
  }

  get pagination() {
    if (this._pagination == null)
      this._pagination = new Pagination();
    return this._pagination;
  }

  set pagination(value) {
    this._pagination = value;
  }

  public set items(value) {
    this._items = value;
  }

  public get item(): Offre {
    if (this._item == null)
      this._item = new Offre();
    return this._item;
  }

  public set item(value: Offre | undefined) {
    this._item = value;
  }

  public get createdItemAfterReturn() {
    let created = {
      item: this.item,
      created: this.toReturn()
    }
    this.returnUrl = undefined
    this.item = undefined
    return created
  }
}

