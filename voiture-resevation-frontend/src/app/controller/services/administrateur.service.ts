import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from 'src/environments/environment';
import {Pagination} from "src/app/controller/utils/pagination/pagination";
import {Administrateur} from 'src/app/controller/entities/administrateur';

@Injectable({providedIn: 'root'})
export class AdministrateurService {
  private readonly api = environment.apiUrl + "administrateur";
  private _item!: Administrateur | undefined;
  private _items!: Array<Administrateur>;
  private _pagination!: Pagination<Administrateur>

  private http = inject(HttpClient)

  public keepData: boolean = false
  public returnUrl: string | undefined = undefined
  public toReturn = () => this.returnUrl != undefined


  public findAll() {
    return this.http.get<Array<Administrateur>>(this.api);
  }

  public findById(id: number) {
    return this.http.get<Administrateur>(`${this.api}/id/${id}`);
  }

  public findAllOptimized() {
    return this.http.get<Array<Administrateur>>(`${this.api}/optimized`);
  }

  public findPaginated(page: number = 0, size: number = 10) {
    return this.http.get<Pagination<Administrateur>>(`${this.api}/paginated?page=${page}&size=${size}`);
  }

  public create() {
    return this.http.post<Administrateur>(this.api, this.item);
  }

  public createList() {
    return this.http.post<Array<Administrateur>>(`${this.api}/all`, this.items);
  }

  public update() {
    return this.http.put<Administrateur>(this.api, this.item);
  }

  public updateList() {
    return this.http.put<Array<Administrateur>>(`${this.api}/all`, this.items);
  }

  public delete(dto: Administrateur) {
    return this.http.delete<number>(this.api, {body: dto});
  }

  public deleteAll(dtos: Array<Administrateur>) {
    return this.http.delete<number>(this.api, {body: dtos});
  }

  public deleteById(id: number) {
    return this.http.delete<number>(`${this.api}/id/${id}`);
  }

  public deleteByReservationsId(id: number) {
    return this.http.delete<number>(`${this.api}/reservations/${id}`);
  }

  public findByReservationsId(id: number) {
    return this.http.get<Array<Administrateur>>(`${this.api}/reservations/${id}`);
  }

  public deleteByOffresId(id: number) {
    return this.http.delete<number>(`${this.api}/offres/${id}`);
  }

  public findByOffresId(id: number) {
    return this.http.get<Array<Administrateur>>(`${this.api}/offres/${id}`);
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

  public get item(): Administrateur {
    if (this._item == null)
      this._item = new Administrateur();
    return this._item;
  }

  public set item(value: Administrateur | undefined) {
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

