import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { LoginService } from './login.service';

export const authGuard: CanActivateFn = (route, state) => {

  const servicoLogin = inject(LoginService);
  if (servicoLogin.isExpired()) {
    servicoLogin.logout();
    return false;
  }

  const papelExigido = route.data['papel'];
  const papelUsuario = servicoLogin.usuarioAutenticado.value.papel;
  return papelExigido == papelUsuario || !papelExigido;
  
};
