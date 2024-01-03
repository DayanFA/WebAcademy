import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { BarraComandosComponent } from '../barra-comandos/barra-comandos.component';
import { ProfissionalListComponent } from './profissional-list.component';

describe('ProfissionalListComponent', () => {
  let component: ProfissionalListComponent;
  let fixture: ComponentFixture<ProfissionalListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        FormsModule
      ],
      declarations: [
        ProfissionalListComponent,
        BarraComandosComponent
      ]
    });
    fixture = TestBed.createComponent(ProfissionalListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
