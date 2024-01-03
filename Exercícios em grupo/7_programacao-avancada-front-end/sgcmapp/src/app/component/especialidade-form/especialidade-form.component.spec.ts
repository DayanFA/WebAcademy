import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { EspecialidadeFormComponent } from './especialidade-form.component';

describe('EspecialidadeFormComponent', () => {
  let component: EspecialidadeFormComponent;
  let fixture: ComponentFixture<EspecialidadeFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule
      ],
      declarations: [EspecialidadeFormComponent]
    });
    fixture = TestBed.createComponent(EspecialidadeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
