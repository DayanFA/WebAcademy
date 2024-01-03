import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectTemaComponent } from './select-tema.component';

describe('SelectTemaComponent', () => {
  let component: SelectTemaComponent;
  let fixture: ComponentFixture<SelectTemaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelectTemaComponent]
    });
    fixture = TestBed.createComponent(SelectTemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
