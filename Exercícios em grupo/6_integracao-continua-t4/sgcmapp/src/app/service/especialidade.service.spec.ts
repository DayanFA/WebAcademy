import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { EspecialidadeService } from './especialidade.service';

describe('EspecialidadeService', () => {
  let service: EspecialidadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(EspecialidadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
