import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ConvenioService } from './convenio.service';

describe('ConvenioService', () => {
  let service: ConvenioService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(ConvenioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
