import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsModule } from '@angular/forms';
import { TemaSelectComponent } from './tema-select.component';

describe('TemaSelectComponent', () => {
  let component: TemaSelectComponent;
  let fixture: ComponentFixture<TemaSelectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule],
      declarations: [TemaSelectComponent]
    });
    fixture = TestBed.createComponent(TemaSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
