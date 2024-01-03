import { Component, OnInit } from '@angular/core';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Paciente } from 'src/app/model/paciente';
import { PageRequest } from 'src/app/model/page-request';
import { PageResponse } from 'src/app/model/page-response';
import { AlertaService } from 'src/app/service/alerta.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { IList } from '../i-list';

@Component({
  selector: 'app-paciente-list',
  templateUrl: './paciente-list.component.html',
  styles: [
  ]
})
export class PacienteListComponent implements OnInit, IList<Paciente> {

  constructor(
    private servico: PacienteService,
    private servicoAlerta: AlertaService
  ) { }

  ngOnInit(): void {
    this.paginaRequisicao.size = parseInt(localStorage.getItem('tamanhoPagina') || PageRequest.DEFAULT_PAGE_SIZE.toString());
    this.get();
  }

  registros: Paciente[] = Array<Paciente>();
  paginaRequisicao: PageRequest = new PageRequest();
  paginaResposta: PageResponse<Paciente> = <PageResponse<Paciente>>{};
  termoBusca: string = '';

  colunas = [
    { campo: 'id', descricao: 'ID' },
    { campo: 'nome', descricao: 'Nome' },
    { campo: 'email', descricao: 'E-mail' },
    { campo: 'telefone', descricao: 'Telefone' },
    { campo: 'dataNascimento', descricao: 'Data de Nascimento' },
    { campo: 'grupoSanguineo', descricao: 'Grupo Sanguíneo' },
    { campo: 'sexo', descricao: 'Sexo' },
    { campo: 'cep', descricao: 'CEP' },
    { campo: 'endereco', descricao: 'Endereço' },
    { campo: 'estado', descricao: 'Estado' },
    { campo: 'cidade', descricao: 'Cidade' },
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
      next: (resposta: PageResponse<Paciente>) => {
        this.registros = resposta.content;
        this.paginaResposta = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir o paciente?')) {
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
