import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { BarraComandosComponent } from '../barra-comandos/barra-comandos.component';
import { PaginacaoComponent } from '../paginacao/paginacao.component';
import { TheadOrdenacaoComponent } from '../thead-ordenacao/thead-ordenacao.component';
import { AtendimentoListComponent } from './atendimento-list.component';

describe('AtendimentoListComponent', () => {
  let component: AtendimentoListComponent;
  let fixture: ComponentFixture<AtendimentoListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        FormsModule
      ],
      declarations: [
        AtendimentoListComponent,
        BarraComandosComponent,
        PaginacaoComponent,
        TheadOrdenacaoComponent
      ]
    });
    fixture = TestBed.createComponent(AtendimentoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
