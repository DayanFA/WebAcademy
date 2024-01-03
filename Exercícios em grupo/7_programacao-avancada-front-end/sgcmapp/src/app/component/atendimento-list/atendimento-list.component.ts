import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Atendimento } from 'src/app/model/atendimento';
import { AtendimentoService } from 'src/app/service/atendimento.service';
import { PageRequest } from '../../model/page-request';
import { PageResponse } from '../../model/page-response';

@Component({
  selector: 'app-atendimento-list',
  templateUrl: './atendimento-list.component.html',
  styles: [
  ]
})
export class AtendimentoListComponent implements IList<Atendimento>, OnInit {

  constructor(private servico: AtendimentoService) {}

  ngOnInit(): void {
    this.get();
  }

  registros: Atendimento[] = Array<Atendimento>();
  status: string[] = ['CHEGADA', 'ATENDIMENTO'];

  get(termoBusca?: string | undefined): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: PageResponse<Atendimento>) => {
        this.registros = resposta.content.filter(item => {
          return this.status.includes(item.status);
        });
      }
    });
  }

  updateStatus(id: number): void {
    if (confirm('Confirma alteração no status do agendamento?')) {
      this.servico.updateStatus(id).subscribe({
        complete: () => {
          this.get();
        }
      });
    }
  }

  delete(id: number): void {
    throw new Error('Method not implemented.');
  }

}
