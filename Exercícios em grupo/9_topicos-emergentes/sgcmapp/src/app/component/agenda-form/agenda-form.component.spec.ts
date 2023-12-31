import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { Convenio } from 'src/app/model/convenio';
import { AgendaFormComponent } from './agenda-form.component';
import { ConvenioService } from 'src/app/service/convenio.service';
import { of } from 'rxjs';
import { PageResponse } from 'src/app/model/page-response';

describe('AgendaFormComponent', () => {
  let component: AgendaFormComponent;
  let fixture: ComponentFixture<AgendaFormComponent>;
  let mockConvenioService: ConvenioService;

  const mockConvenios: PageResponse<Convenio> = {
    content: [
      {
        id: 1,
        ativo: true,
        nome: 'Convenio A',
        cnpj: '',
        email: '',
        razaoSocial: '',
        telefone: '',
        representante: ''
      },
      {
        id: 2,
        ativo: true,
        nome: 'Convenio B',
        cnpj: '',
        email: '',
        razaoSocial: '',
        telefone: '',
        representante: ''
      }
    ],
    totalPages: 1,
    totalElements: 2,
    size: 5,
    number: 0,
    numberOfElements: 2
  };

  beforeEach(() => {

    mockConvenioService = jasmine.createSpyObj('ConvenioService', {
      getAll: of(mockConvenios)
    });

    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule
      ],
      declarations: [AgendaFormComponent],
      providers: [
        { provide: ConvenioService, useValue: mockConvenioService }
      ]
    });

    fixture = TestBed.createComponent(AgendaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should populate dropdown based on Convenio list', () => {
    component.convenios = mockConvenios.content;
    fixture.detectChanges();

    const select = fixture.nativeElement.querySelector('select#convenio');
    const options = select.querySelectorAll('option');

    expect(options.length).toEqual(3);
    expect(options[1].text).toEqual('Convenio A');
    expect(options[2].text).toEqual('Convenio B');
  });

  it('should populate "convenios" array', () => {
    component.ngOnInit();
    expect(component.convenios).toEqual(mockConvenios.content);
  });
});
