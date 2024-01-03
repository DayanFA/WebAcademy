import { Injectable } from '@angular/core';
import { IService } from './i-service';
import { Profissional } from '../model/profisisonal';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfissionalService implements IService<Profissional> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/profissional/';

  get(termoBusca?: string | undefined): Observable<Profissional[]> {
    let url = this.apiUrl;
    if (termoBusca) {
      url += "busca/" + termoBusca;
    }
    return this.http.get<Profissional[]>(url);
  }

  getById(id: number): Observable<Profissional> {
    throw new Error('Method not implemented.');
  }

  save(objeto: Profissional): Observable<Profissional> {
    throw new Error('Method not implemented.');
  }

  delete(id: number): Observable<void> {
    throw new Error('Method not implemented.');
  }

}
