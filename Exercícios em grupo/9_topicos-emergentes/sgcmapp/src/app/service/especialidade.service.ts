import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Especialidade } from '../model/especialidade';
import { PageRequest } from '../model/page-request';
import { PageResponse } from '../model/page-response';
import { IService } from './i-service';

@Injectable({
  providedIn: 'root'
})
export class EspecialidadeService implements IService<Especialidade> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/config/especialidade/';

  get(termoBusca?: string | undefined, pageRequest?: PageRequest): Observable<PageResponse<Especialidade>> {
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
    return this.http.get<PageResponse<Especialidade>>(url);
  }

  getAll(): Observable<PageResponse<Especialidade>> {
    let url = this.apiUrl + 'all';
    return this.http.get<PageResponse<Especialidade>>(url);
  }
  
  getById(id: number): Observable<Especialidade> {
    let url = this.apiUrl + id;
    return this.http.get<Especialidade>(url);
  }

  save(objeto: Especialidade): Observable<Especialidade> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Especialidade>(url, objeto);
    } else {
      return this.http.post<Especialidade>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }

}
