import { TestBed } from '@angular/core/testing';

import { HttpClientModule } from '@angular/common/http';
import { ErroInterceptor } from './erro.interceptor';

describe('ErroInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientModule],
    providers: [
      ErroInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: ErroInterceptor = TestBed.inject(ErroInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
