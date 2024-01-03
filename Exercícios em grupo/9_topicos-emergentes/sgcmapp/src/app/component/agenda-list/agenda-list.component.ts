import { Component, OnInit } from '@angular/core';
import { Atendimento } from 'src/app/model/atendimento';
import { PageRequest } from 'src/app/model/page-request';
import { PageResponse } from 'src/app/model/page-response';
import { AtendimentoService } from 'src/app/service/atendimento.service';
import { IList } from '../i-list';

@Component({
  selector: 'app-agenda-list',
  templateUrl: './agenda-list.component.html',
  styles: [
  ]
})
export class AgendaListComponent implements IList<Atendimento>, OnInit {

  constructor(private servico: AtendimentoService) {}

  ngOnInit(): void {
    this.paginaRequisicao.size = parseInt(localStorage.getItem('tamanhoPagina') || PageRequest.DEFAULT_PAGE_SIZE.toString());
    this.get();
  }

  registros: Atendimento[] = Array<Atendimento>();
  paginaRequisicao: PageRequest = new PageRequest();
  paginaResposta: PageResponse<Atendimento> = <PageResponse<Atendimento>>{};
  termoBusca: string = '';

  colunas = [
    { campo: 'data', descricao: 'Data' },
    { campo: 'hora', descricao: 'Hora' },
    { campo: 'paciente.nome', descricao: 'Paciente' },
    { campo: 'profissional.nome', descricao: 'Profissional' },
    { campo: 'profissional.unidade.nome', descricao: 'Unidade' },
    { campo: 'convenio.nome', descricao: 'Convênio' },
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
    this.servico.getAgendamentos(termoBusca, this.paginaRequisicao).subscribe({
      next: (resposta: PageResponse<Atendimento>) => {
        this.registros = resposta.content;
        this.paginaResposta = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja cancelar o agendamento?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get(this.termoBusca);
        }
      });
    }
  }

  updateStatus(id: number): void {
    if (confirm('Confirma alteração no status do agendamento?')) {
      this.servico.updateStatus(id).subscribe({
        complete: () => {
          this.get(this.termoBusca);
        }
      });
    }
  }

}
