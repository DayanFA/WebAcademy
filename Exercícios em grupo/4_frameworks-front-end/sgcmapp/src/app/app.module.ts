import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AgendaListComponent } from './component/agenda-list/agenda-list.component';
import { AgendaFormComponent } from './component/agenda-form/agenda-form.component';
import { AtendimentoListComponent } from './component/atendimento-list/atendimento-list.component';
import { FormsModule } from '@angular/forms';
import { BarraComandosComponent } from './component/barra-comandos/barra-comandos.component';
import { AlertaComponent } from './component/alerta/alerta.component';
import { ErroInterceptor } from './interceptor/erro.interceptor';
import { UsuarioListComponent } from './component/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './component/usuario-form/usuario-form.component';
import { UnidadeListComponent } from './component/unidade-list/unidade-list.component';
import { UnidadeFormComponent } from './component/unidade-form/unidade-form.component';
import { EspecialidadeListComponent } from './component/especialidade-list/especialidade-list.component';
import { EspecialidadeFormComponent } from './component/especialidade-form/especialidade-form.component';
import { ConvenioFormComponent } from './component/convenio-form/convenio-form.component';
import { ConvenioListComponent } from './component/convenio-list/convenio-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AgendaListComponent,
    AgendaFormComponent,
    AtendimentoListComponent,
    BarraComandosComponent,
    AlertaComponent,
    UsuarioListComponent,
    UsuarioFormComponent,
    UnidadeListComponent,
    UnidadeFormComponent,
    EspecialidadeListComponent,
    EspecialidadeFormComponent,
    ConvenioFormComponent,
    ConvenioListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: ErroInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
