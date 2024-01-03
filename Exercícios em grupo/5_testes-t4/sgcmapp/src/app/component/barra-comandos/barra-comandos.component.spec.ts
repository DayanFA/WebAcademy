import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BarraComandosComponent } from './barra-comandos.component';
import { FormsModule } from '@angular/forms';

describe('BarraComandosComponent', () => {
  let component: BarraComandosComponent;
  let fixture: ComponentFixture<BarraComandosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule],
      declarations: [BarraComandosComponent]
    });
    fixture = TestBed.createComponent(BarraComandosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
