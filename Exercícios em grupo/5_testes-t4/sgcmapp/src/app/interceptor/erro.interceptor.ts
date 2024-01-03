import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { AlertaService } from '../service/alerta.service';
import { ETipoAlerta } from '../model/e-tipo-alerta';

@Injectable()
export class ErroInterceptor implements HttpInterceptor {

  constructor(private servicoAlerta: AlertaService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(catchError(
      (erro) => this.processaErro(erro)
    ));
  }

  processaErro(erro: HttpErrorResponse): Observable<any> {
    
    let mensagemErro = erro.error?.message || erro.statusText;

    this.servicoAlerta.enviarAlerta({
      tipo: ETipoAlerta.ERRO,
      mensagem: mensagemErro
    });

    return throwError(() => erro);

  }

}
