import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnidadeFormComponent } from './unidade-form.component';

describe('UnidadeFormComponent', () => {
  let component: UnidadeFormComponent;
  let fixture: ComponentFixture<UnidadeFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UnidadeFormComponent]
    });
    fixture = TestBed.createComponent(UnidadeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
