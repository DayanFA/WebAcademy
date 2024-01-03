import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { BarraComandosComponent } from '../barra-comandos/barra-comandos.component';
import { PaginacaoComponent } from '../paginacao/paginacao.component';
import { TheadOrdenacaoComponent } from '../thead-ordenacao/thead-ordenacao.component';
import { EspecialidadeListComponent } from './especialidade-list.component';

describe('EspecialidadeListComponent', () => {
  let component: EspecialidadeListComponent;
  let fixture: ComponentFixture<EspecialidadeListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        FormsModule
      ],
      declarations: [
        EspecialidadeListComponent,
        BarraComandosComponent,
        PaginacaoComponent,
        TheadOrdenacaoComponent
      ]
    });
    fixture = TestBed.createComponent(EspecialidadeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
