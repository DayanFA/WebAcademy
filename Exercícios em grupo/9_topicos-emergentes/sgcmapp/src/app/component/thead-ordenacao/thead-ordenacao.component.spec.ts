import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TheadOrdenacaoComponent } from './thead-ordenacao.component';

describe('TheadOrdenacaoComponent', () => {
  let component: TheadOrdenacaoComponent;
  let fixture: ComponentFixture<TheadOrdenacaoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TheadOrdenacaoComponent]
    });
    fixture = TestBed.createComponent(TheadOrdenacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
