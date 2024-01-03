import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../model/usuario';
import { environment } from 'src/environments/environment';
import { TokenResponse } from '../model/token-response';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario') || '{}');
    this.usuarioAutenticado.next(this.usuario);
  }

  private usuario: Usuario = <Usuario>{};
  usuarioAutenticado: BehaviorSubject<Usuario> = new BehaviorSubject<Usuario>(this.usuario);

  login(usuario: Usuario): void {
    let url = environment.API_URL + '/login';
    this.http.post<TokenResponse>(url, usuario).subscribe({
      next: (resposta: TokenResponse) => {

        const token = resposta.token;
        const decodedToken = JSON.parse(atob(token.split('.')[1]));
        const tokenExpiration = decodedToken.exp * 1000;

        usuario.nomeUsuario = decodedToken.sub;
        usuario.nomeCompleto = decodedToken.nomeCompleto;
        usuario.papel = decodedToken.papel;

        sessionStorage.setItem('token', token);
        sessionStorage.setItem('usuario', JSON.stringify(usuario));
        sessionStorage.setItem('tokenExpiration', tokenExpiration.toString());

        this.usuarioAutenticado.next(usuario);

      },
      complete: () => {
        this.router.navigate(['/']);
      }
    });
  }

  logout(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('usuario');
    sessionStorage.removeItem('tokenExpiration');
    this.router.navigate(['/login']);
  }

  isExpired(): boolean {
    const expiration = sessionStorage.getItem('tokenExpiration');
    const expirationDate = new Date(Number(expiration));
    return expirationDate < new Date();
  }

}
