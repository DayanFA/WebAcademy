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
import { LoginService } from '../service/login.service';

@Injectable()
export class ErroInterceptor implements HttpInterceptor {

  constructor(
    private servicoAlerta: AlertaService,
    private loginService: LoginService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(catchError(
      (erro) => this.processaErro(erro)
    ));
  }

  processaErro(erro: HttpErrorResponse): Observable<any> {
    
    let mensagemErro = erro.error?.message || erro.statusText;

    if ([401, 403].includes(erro.status)) {
      mensagemErro = 'Acesso não autorizado!';
      this.loginService.logout();
    }

    this.servicoAlerta.enviarAlerta({
      tipo: ETipoAlerta.ERRO,
      mensagem: mensagemErro
    });

    return throwError(() => erro);

  }

}
