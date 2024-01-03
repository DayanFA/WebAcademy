import { TestBed } from '@angular/core/testing';

import { ProfissionalService } from './profissional.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ProfissionalService', () => {
  let service: ProfissionalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(ProfissionalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
