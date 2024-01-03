import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { LoginService } from './service/login.service';
import { Usuario } from './model/usuario';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'SGCM';
  currentUrl: string = '';
  usuario: Usuario = <Usuario>{};

  constructor(
    router: Router,
    private servicoLogin: LoginService
    ) {

    router.events.subscribe(evento => {
      if (evento instanceof NavigationEnd) {
        if (evento.url != '') {
          this.currentUrl = evento.url;
        } else {
          this.currentUrl = '';
        }
      }
    });

    this.servicoLogin.usuarioAutenticado.subscribe({
      next: (usuario: Usuario) => {
        this.usuario = usuario;
      }
    });

  }

  isLogin(): boolean {
    return this.currentUrl == '/login';
  }

  isAdmin(): boolean {
    return this.usuario.papel == 'ROLE_ADMIN';
  }

  logout(): void {
    this.servicoLogin.logout();
  }

}
