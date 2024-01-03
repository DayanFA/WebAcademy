import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Unidade } from '../model/unidade';
import { IService } from './i-service';
import { PageRequest } from '../model/page-request';
import { PageResponse } from '../model/page-response';

@Injectable({
  providedIn: 'root'
})
export class UnidadeService implements IService<Unidade> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/config/unidade/';

  get(termoBusca?: string | undefined, pageRequest?: PageRequest): Observable<PageResponse<Unidade>> {
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
    return this.http.get<PageResponse<Unidade>>(url);
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
