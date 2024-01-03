import { Component, OnInit } from '@angular/core';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { PageRequest } from 'src/app/model/page-request';
import { PageResponse } from 'src/app/model/page-response';
import { Unidade } from 'src/app/model/unidade';
import { AlertaService } from 'src/app/service/alerta.service';
import { UnidadeService } from 'src/app/service/unidade.service';
import { IList } from '../i-list';

@Component({
  selector: 'app-unidade-list',
  templateUrl: './unidade-list.component.html',
  styles: [
  ]
})
export class UnidadeListComponent implements OnInit, IList<Unidade> {

  constructor(
    private servico: UnidadeService,
    private servicoAlerta: AlertaService
  ) { }

  ngOnInit(): void {
    this.paginaRequisicao.size = parseInt(localStorage.getItem('tamanhoPagina') || PageRequest.DEFAULT_PAGE_SIZE.toString());
    this.get();
  }

  registros: Unidade[] = Array<Unidade>();
  paginaRequisicao: PageRequest = new PageRequest();
  paginaResposta: PageResponse<Unidade> = <PageResponse<Unidade>>{};
  termoBusca: string = '';

  colunas = [
    { campo: 'id', descricao: 'ID' },
    { campo: 'nome', descricao: 'Nome' },
    { campo: 'endereco', descricao: 'Endereço' },
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
      next: (resposta: PageResponse<Unidade>) => {
        this.registros = resposta.content;
        this.paginaResposta = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir a unidade?')) {
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
