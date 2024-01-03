import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaginacaoComponent } from './paginacao.component';

describe('PaginacaoComponent', () => {
  let component: PaginacaoComponent;
  let fixture: ComponentFixture<PaginacaoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PaginacaoComponent]
    });
    fixture = TestBed.createComponent(PaginacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
