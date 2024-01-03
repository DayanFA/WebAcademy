import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PageRequest } from '../model/page-request';
import { PageResponse } from '../model/page-response';
import { Profissional } from '../model/profissional';
import { IService } from './i-service';

@Injectable({
  providedIn: 'root'
})
export class ProfissionalService implements IService<Profissional> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/profissional/';

  get(termoBusca?: string | undefined, pageRequest?: PageRequest): Observable<PageResponse<Profissional>> {
    let url = this.apiUrl;
    if (termoBusca) {
      url += "busca/" + termoBusca;
    }
    if (pageRequest) {
      url += "?page=" + pageRequest.page + "&size=" + pageRequest.size;
      pageRequest.sort.forEach(campo => {
        url += "&sort=" + campo;
      });
    }
    return this.http.get<PageResponse<Profissional>>(url);
  }

  getAll(): Observable<PageResponse<Profissional>> {
    let url = this.apiUrl + "all";
    return this.http.get<PageResponse<Profissional>>(url);
  }

  getById(id: number): Observable<Profissional> {
    let url = this.apiUrl + id;
    return this.http.get<Profissional>(url);
  }

  save(objeto: Profissional): Observable<Profissional> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Profissional>(url, objeto);
    } else {
      return this.http.post<Profissional>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }

}
