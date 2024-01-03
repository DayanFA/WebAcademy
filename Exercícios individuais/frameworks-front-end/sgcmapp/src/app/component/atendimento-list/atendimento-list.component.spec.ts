import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtendimentoListComponent } from './atendimento-list.component';

describe('AtendimentoListComponent', () => {
  let component: AtendimentoListComponent;
  let fixture: ComponentFixture<AtendimentoListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtendimentoListComponent]
    });
    fixture = TestBed.createComponent(AtendimentoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
