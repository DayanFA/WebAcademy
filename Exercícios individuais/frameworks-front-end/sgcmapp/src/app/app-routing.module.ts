import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AgendaListComponent } from './component/agenda-list/agenda-list.component';
import { AgendaFormComponent } from './component/agenda-form/agenda-form.component';
import { AtendimentoListComponent } from './component/atendimento-list/atendimento-list.component';
import { UsuarioListComponent } from './component/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './component/usuario-form/usuario-form.component';

const routes: Routes = [
  { path: 'agenda', component: AgendaListComponent },
  { path: 'agenda/form', component: AgendaFormComponent },
  { path: 'atendimento', component: AtendimentoListComponent },
  { path: 'config/usuarios', component: UsuarioListComponent },
  { path: 'config/usuarios/form', component: UsuarioFormComponent }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
