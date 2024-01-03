import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Paciente } from '../model/paciente';
import { PageRequest } from '../model/page-request';
import { PageResponse } from '../model/page-response';
import { IService } from './i-service';

@Injectable({
  providedIn: 'root'
})
export class PacienteService implements IService<Paciente> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/paciente/';

  get(termoBusca?: string | undefined, pageRequest?: PageRequest): Observable<PageResponse<Paciente>> {
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
    return this.http.get<PageResponse<Paciente>>(url);
  }

  getAll(): Observable<PageResponse<Paciente>> {
    let url = this.apiUrl + "all";
    return this.http.get<PageResponse<Paciente>>(url);
  }

  getById(id: number): Observable<Paciente> {
    let url = this.apiUrl + id;
    return this.http.get<Paciente>(url);
  }

  save(objeto: Paciente): Observable<Paciente> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Paciente>(url, objeto);
    } else {
      return this.http.post<Paciente>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }

}
