import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Unidade } from '../model/unidade';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UnidadeService implements IService<Unidade> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + "/config/unidade/";

  get(termoBusca?: string | undefined): Observable<Unidade[]> {
    let url = this.apiUrl;
    if (termoBusca) {
      url += "busca/" + termoBusca;
    }
    return this.http.get<Unidade[]>(url);
  }

  getById(id: number): Observable<Unidade> {
    let url = this.apiUrl + id;
    return this.http.get<Unidade>(url);
  }

  save(objeto: Unidade): Observable<Unidade> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Unidade>(url, objeto);
    } else {
      return this.http.post<Unidade>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }

}