import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Alerta } from '../model/alerta';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {

  private controleAlerta: Subject<Alerta>;

  constructor() {
    this.controleAlerta = new Subject<Alerta>();
  }

  enviarAlerta(alerta: Alerta): void {
    this.controleAlerta.next(alerta);
  }

  receberAlerta(): Observable<Alerta> {
    return this.controleAlerta.asObservable();
  }

}
