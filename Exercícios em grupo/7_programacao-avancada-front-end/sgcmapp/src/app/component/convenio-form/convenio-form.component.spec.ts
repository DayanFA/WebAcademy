import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConvenioFormComponent } from './convenio-form.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';

describe('ConvenioFormComponent', () => {
  let component: ConvenioFormComponent;
  let fixture: ComponentFixture<ConvenioFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule
      ],
      declarations: [ConvenioFormComponent]
    });
    fixture = TestBed.createComponent(ConvenioFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
