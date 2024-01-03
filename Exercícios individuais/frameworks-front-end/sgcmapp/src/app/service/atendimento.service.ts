import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Atendimento } from '../model/atendimento';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AtendimentoService implements IService<Atendimento> {
  
  getHorarios(idProfissional: number, data:string): Observable<string[]> { 
    let url = this.apiUrl;
    if(idProfissional && data){
      url += "horarios/" + idProfissional + "/" + data;
    }
    return this.http.get<string[]>(url);
  }
  
  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + "/atendimento/";

  get(termoBusca?: string | undefined): Observable<Atendimento[]> {
    let url = this.apiUrl;
    if (termoBusca) {
      url += "busca/" + termoBusca;
    }
    return this.http.get<Atendimento[]>(url);
  }

  getById(id: number): Observable<Atendimento> {
    let url = this.apiUrl + id;
    return this.http.get<Atendimento>(url);
  }

  save(objeto: Atendimento): Observable<Atendimento> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Atendimento>(url, objeto);
    } else {
      return this.http.post<Atendimento>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }

  updateStatus(id: number): Observable<Atendimento> {
    let url = this.apiUrl + "status/" + id;
    return this.http.put<Atendimento>(url, null);
  }

}
