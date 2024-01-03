import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AgendaFormComponent } from './component/agenda-form/agenda-form.component';
import { AgendaListComponent } from './component/agenda-list/agenda-list.component';
import { AtendimentoListComponent } from './component/atendimento-list/atendimento-list.component';
import { PacienteFormComponent } from './component/paciente-form/paciente-form.component';
import { PacienteListComponent } from './component/paciente-list/paciente-list.component';
import { ProfissionalFormComponent } from './component/profissional-form/profissional-form.component';
import { ProfissionalListComponent } from './component/profissional-list/profissional-list.component';
import { ConvenioListComponent } from './component/convenio-list/convenio-list.component';
import { ConvenioFormComponent } from './component/convenio-form/convenio-form.component';
import { UnidadeListComponent } from './component/unidade-list/unidade-list.component';
import { UnidadeFormComponent } from './component/unidade-form/unidade-form.component';
import { EspecialidadeListComponent } from './component/especialidade-list/especialidade-list.component';
import { EspecialidadeFormComponent } from './component/especialidade-form/especialidade-form.component';
import { UsuarioListComponent } from './component/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './component/usuario-form/usuario-form.component';

const routes: Routes = [
  { path: 'agenda', component: AgendaListComponent },
  { path: 'agenda/form', component: AgendaFormComponent },
  { path: 'atendimento', component: AtendimentoListComponent},    
  { path: 'pacientes', component: PacienteListComponent },
  { path: 'pacientes/form', component: PacienteFormComponent },
  { path: 'profissionais', component: ProfissionalListComponent },
  { path: 'profissionais/form', component: ProfissionalFormComponent },
  { path: 'convenios', component: ConvenioListComponent},
  { path: 'convenios/form', component: ConvenioFormComponent},
  { path: 'config', children: [
    { path: 'unidades', component: UnidadeListComponent },
    { path: 'unidades/form', component: UnidadeFormComponent },
    { path: 'especialidades', component: EspecialidadeListComponent },
    { path: 'especialidades/form', component: EspecialidadeFormComponent },
    { path: 'usuarios', component: UsuarioListComponent },
    { path: 'usuarios/form', component: UsuarioFormComponent }
  ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
