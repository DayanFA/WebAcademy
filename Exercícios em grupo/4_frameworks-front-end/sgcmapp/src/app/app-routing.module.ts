import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AgendaListComponent } from './component/agenda-list/agenda-list.component';
import { AgendaFormComponent } from './component/agenda-form/agenda-form.component';
import { AtendimentoListComponent } from './component/atendimento-list/atendimento-list.component';
import { UsuarioListComponent } from './component/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './component/usuario-form/usuario-form.component';
import { UnidadeListComponent } from './component/unidade-list/unidade-list.component';
import { UnidadeFormComponent } from './component/unidade-form/unidade-form.component';
import { ConvenioListComponent } from './component/convenio-list/convenio-list.component';
import { ConvenioFormComponent } from './component/convenio-form/convenio-form.component';
import { EspecialidadeListComponent } from './component/especialidade-list/especialidade-list.component';
import { EspecialidadeFormComponent } from './component/especialidade-form/especialidade-form.component';

const routes: Routes = [
  { path: 'agenda', component: AgendaListComponent },
  { path: 'agenda/form', component: AgendaFormComponent },
  { path: 'atendimento', component: AtendimentoListComponent },
  { path: 'config/usuarios', component: UsuarioListComponent },
  { path: 'config/usuarios/form', component: UsuarioFormComponent }, 
  { path: 'config/unidades', component: UnidadeListComponent },
  { path: 'config/unidades/form', component: UnidadeFormComponent },
  { path: 'convenios', component: ConvenioListComponent },
  { path: 'convenios/form', component: ConvenioFormComponent },
  { path: 'config/especialidades', component: EspecialidadeListComponent },
  { path: 'config/especialidades/form', component: EspecialidadeFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
