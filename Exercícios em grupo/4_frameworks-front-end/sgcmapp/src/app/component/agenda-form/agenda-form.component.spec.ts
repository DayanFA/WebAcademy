import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendaFormComponent } from './agenda-form.component';

describe('AgendaFormComponent', () => {
  let component: AgendaFormComponent;
  let fixture: ComponentFixture<AgendaFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgendaFormComponent]
    });
    fixture = TestBed.createComponent(AgendaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
