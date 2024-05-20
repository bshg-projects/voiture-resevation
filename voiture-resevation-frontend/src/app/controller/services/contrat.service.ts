import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from 'src/environments/environment';
import {Pagination} from "src/app/controller/utils/pagination/pagination";
import {Contrat} from 'src/app/controller/entities/contrat';

@Injectable({providedIn: 'root'})
export class ContratService {
  private readonly api = environment.apiUrl + "contrat";
  private _item!: Contrat | undefined;
  private _items!: Array<Contrat>;
  private _pagination!: Pagination<Contrat>

  private http = inject(HttpClient)

  public keepData: boolean = false
  public returnUrl: string | undefined = undefined
  public toReturn = () => this.returnUrl != undefined


  public findAll() {
    return this.http.get<Array<Contrat>>(this.api);
  }

  public findById(id: number) {
    return this.http.get<Contrat>(`${this.api}/id/${id}`);
  }

  public findAllOptimized() {
    return this.http.get<Array<Contrat>>(`${this.api}/optimized`);
  }

  public findPaginated(page: number = 0, size: number = 10) {
    return this.http.get<Pagination<Contrat>>(`${this.api}/paginated?page=${page}&size=${size}`);
  }

  public create() {
    return this.http.post<Contrat>(this.api, this.item);
  }

  public createList() {
    return this.http.post<Array<Contrat>>(`${this.api}/all`, this.items);
  }

  public update() {
    return this.http.put<Contrat>(this.api, this.item);
  }

  public updateList() {
    return this.http.put<Array<Contrat>>(`${this.api}/all`, this.items);
  }

  public delete(dto: Contrat) {
    return this.http.delete<number>(this.api, {body: dto});
  }

  public deleteAll(dtos: Array<Contrat>) {
    return this.http.delete<number>(this.api, {body: dtos});
  }

  public deleteById(id: number) {
    return this.http.delete<number>(`${this.api}/id/${id}`);
  }

  public deleteByAdministrateurId(id: number) {
    return this.http.delete<number>(`${this.api}/administrateur/${id}`);
  }

  public findByAdministrateurId(id: number) {
    return this.http.get<Array<Contrat>>(`${this.api}/administrateur/${id}`);
  }

  public deleteByClientId(id: number) {
    return this.http.delete<number>(`${this.api}/client/${id}`);
  }

  public findByClientId(id: number) {
    return this.http.get<Array<Contrat>>(`${this.api}/client/${id}`);
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

  public get item(): Contrat {
    if (this._item == null)
      this._item = new Contrat();
    return this._item;
  }

  public set item(value: Contrat | undefined) {
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

