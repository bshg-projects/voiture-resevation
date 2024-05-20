import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from 'src/environments/environment';
import {Pagination} from "src/app/controller/utils/pagination/pagination";
import {Reservation} from 'src/app/controller/entities/reservation';

@Injectable({providedIn: 'root'})
export class ReservationService {
  private readonly api = environment.apiUrl + "reservation";
  private _item!: Reservation | undefined;
  private _items!: Array<Reservation>;
  private _pagination!: Pagination<Reservation>

  private http = inject(HttpClient)

  public keepData: boolean = false
  public returnUrl: string | undefined = undefined
  public toReturn = () => this.returnUrl != undefined


  public findAll() {
    return this.http.get<Array<Reservation>>(this.api);
  }

  public findById(id: number) {
    return this.http.get<Reservation>(`${this.api}/id/${id}`);
  }

  public findAllOptimized() {
    return this.http.get<Array<Reservation>>(`${this.api}/optimized`);
  }

  public findPaginated(page: number = 0, size: number = 10) {
    return this.http.get<Pagination<Reservation>>(`${this.api}/paginated?page=${page}&size=${size}`);
  }

  public create() {
    return this.http.post<Reservation>(this.api, this.item);
  }

  public createList() {
    return this.http.post<Array<Reservation>>(`${this.api}/all`, this.items);
  }

  public update() {
    return this.http.put<Reservation>(this.api, this.item);
  }

  public updateList() {
    return this.http.put<Array<Reservation>>(`${this.api}/all`, this.items);
  }

  public delete(dto: Reservation) {
    return this.http.delete<number>(this.api, {body: dto});
  }

  public deleteAll(dtos: Array<Reservation>) {
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

  public get item(): Reservation {
    if (this._item == null)
      this._item = new Reservation();
    return this._item;
  }

  public set item(value: Reservation | undefined) {
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

