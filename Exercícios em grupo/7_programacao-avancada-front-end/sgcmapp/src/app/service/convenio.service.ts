import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Convenio } from '../model/convenio';
import { IService } from './i-service';
import { PageRequest } from '../model/page-request';
import { PageResponse } from '../model/page-response';

@Injectable({
  providedIn: 'root'
})
export class ConvenioService implements IService<Convenio> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/convenio/';

  get(termoBusca?: string | undefined, pageRequest?: PageRequest): Observable<PageResponse<Convenio>> {
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
    return this.http.get<PageResponse<Convenio>>(url);
  }

  getById(id: number): Observable<Convenio> {
    let url = this.apiUrl + id;
    return this.http.get<Convenio>(url);
  }

  save(objeto: Convenio): Observable<Convenio> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Convenio>(url, objeto);
    } else {
      return this.http.post<Convenio>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }

}
