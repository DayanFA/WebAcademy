import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from '../model/usuario';
import { IService } from './i-service';
import { PageRequest } from '../model/page-request';
import { PageResponse } from '../model/page-response';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService implements IService<Usuario> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/config/usuario/';

  get(termoBusca?: string | undefined, pageRequest?: PageRequest): Observable<PageResponse<Usuario>> {
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
    return this.http.get<PageResponse<Usuario>>(url);
  }

  getById(id: number): Observable<Usuario> {
    let url = this.apiUrl + id;
    return this.http.get<Usuario>(url);
  }

  save(objeto: Usuario): Observable<Usuario> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Usuario>(url, objeto);
    } else {
      return this.http.post<Usuario>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
  
}
