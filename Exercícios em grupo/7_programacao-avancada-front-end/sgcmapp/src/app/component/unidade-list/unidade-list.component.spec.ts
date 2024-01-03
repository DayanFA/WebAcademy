import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { BarraComandosComponent } from '../barra-comandos/barra-comandos.component';
import { UnidadeListComponent } from './unidade-list.component';

describe('UnidadeListComponent', () => {
  let component: UnidadeListComponent;
  let fixture: ComponentFixture<UnidadeListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        FormsModule
      ],
      declarations: [
        UnidadeListComponent,
        BarraComandosComponent
      ]
    });
    fixture = TestBed.createComponent(UnidadeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
