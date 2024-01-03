import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EspecialidadeListComponent } from './especialidade-list.component';

describe('EspecialidadeListComponent', () => {
  let component: EspecialidadeListComponent;
  let fixture: ComponentFixture<EspecialidadeListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EspecialidadeListComponent]
    });
    fixture = TestBed.createComponent(EspecialidadeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
