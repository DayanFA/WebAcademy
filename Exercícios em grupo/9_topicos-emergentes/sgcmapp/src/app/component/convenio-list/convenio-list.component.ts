import { Component, OnInit } from '@angular/core';
import { Convenio } from 'src/app/model/convenio';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { PageRequest } from 'src/app/model/page-request';
import { PageResponse } from 'src/app/model/page-response';
import { AlertaService } from 'src/app/service/alerta.service';
import { ConvenioService } from 'src/app/service/convenio.service';
import { IList } from '../i-list';

@Component({
  selector: 'app-convenio-list',
  templateUrl: './convenio-list.component.html',
  styles: [
  ]
})
export class ConvenioListComponent implements OnInit, IList<Convenio> {

  constructor(
    private servico: ConvenioService,
    private servicoAlerta: AlertaService
  ) { }

  ngOnInit(): void {
    this.paginaRequisicao.size = parseInt(localStorage.getItem('tamanhoPagina') || PageRequest.DEFAULT_PAGE_SIZE.toString());
    this.get();
  }

  registros: Convenio[] = Array<Convenio>();
  paginaRequisicao: PageRequest = new PageRequest();
  paginaResposta: PageResponse<Convenio> = <PageResponse<Convenio>>{};
  termoBusca: string = '';

  colunas = [
    { campo: 'id', descricao: 'ID' },
    { campo: 'nome', descricao: 'Nome' },
    { campo: 'razaoSocial', descricao: 'Razão Social' },
    { campo: 'cnpj', descricao: 'CNPJ' },
    { campo: 'representante', descricao: 'Representante' },
    { campo: 'email', descricao: 'E-mail' },
    { campo: 'telefone', descricao: 'Telefone' },
    { campo: 'ativo', descricao: 'Ativo' },
    { campo: '', descricao: 'Ações' }
  ];

  buscar(termoBusca: string): void {
    this.termoBusca = termoBusca;
    this.paginaRequisicao.page = 0;
    this.get(this.termoBusca);
  }

  ordenar(ordenacao: string[]): void {
    this.paginaRequisicao.sort = ordenacao;
    this.paginaRequisicao.page = 0;
    this.get(this.termoBusca);
  }

  mudarPagina(pagina: number): void {
    this.paginaRequisicao.page = pagina;
    this.get(this.termoBusca);
  }

  mudarTamanhoPagina(tamanho: number): void {
    this.paginaRequisicao.size = tamanho;
    this.paginaRequisicao.page = 0;
    this.get(this.termoBusca);
  }

  get(termoBusca?: string | undefined): void {
    this.servico.get(termoBusca, this.paginaRequisicao).subscribe({
      next: (resposta: PageResponse<Convenio>) => {
        this.registros = resposta.content;
        this.paginaResposta = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir o convênio?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get(this.termoBusca);
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Operação realizada com sucesso."
          });
        }
      });
    }
  }

}
