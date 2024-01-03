import { Component, OnInit } from '@angular/core';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Especialidade } from 'src/app/model/especialidade';
import { PageRequest } from 'src/app/model/page-request';
import { PageResponse } from 'src/app/model/page-response';
import { AlertaService } from 'src/app/service/alerta.service';
import { EspecialidadeService } from 'src/app/service/especialidade.service';
import { IList } from '../i-list';

@Component({
  selector: 'app-especialidade-list',
  templateUrl: './especialidade-list.component.html',
  styles: [
  ]
})
export class EspecialidadeListComponent implements OnInit, IList<Especialidade> {

  constructor(
    private servico: EspecialidadeService,
    private servicoAlerta: AlertaService
  ) { }

  ngOnInit(): void {
    this.paginaRequisicao.size = parseInt(localStorage.getItem('tamanhoPagina') || PageRequest.DEFAULT_PAGE_SIZE.toString());
    this.get();
  }

  registros: Especialidade[] = Array<Especialidade>();
  paginaRequisicao: PageRequest = new PageRequest();
  paginaResposta: PageResponse<Especialidade> = <PageResponse<Especialidade>>{};
  termoBusca: string = '';

  colunas = [
    { campo: 'id', descricao: 'ID' },
    { campo: 'nome', descricao: 'Nome' },
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
      next: (resposta: PageResponse<Especialidade>) => {
        this.registros = resposta.content;
        this.paginaResposta = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir a especialidade?')) {
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
