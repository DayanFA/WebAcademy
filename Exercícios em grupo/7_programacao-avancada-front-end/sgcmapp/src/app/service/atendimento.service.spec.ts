import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AtendimentoService } from './atendimento.service';

describe('AtendimentoService', () => {
  let service: AtendimentoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(AtendimentoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
