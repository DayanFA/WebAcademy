import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendaListComponent } from './agenda-list.component';

describe('AgendaListComponent', () => {
  let component: AgendaListComponent;
  let fixture: ComponentFixture<AgendaListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgendaListComponent]
    });
    fixture = TestBed.createComponent(AgendaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
