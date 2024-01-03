import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Atendimento } from 'src/app/model/atendimento';
import { AtendimentoService } from 'src/app/service/atendimento.service';

@Component({
  selector: 'app-agenda-list',
  templateUrl: './agenda-list.component.html',
  styles: [
  ]
})
export class AgendaListComponent implements IList<Atendimento>, OnInit {

  constructor(private servico: AtendimentoService) {}

  ngOnInit(): void {
    this.get();
  }

  registros: Atendimento[] = Array<Atendimento>();
  status: string[] = ['AGENDADO', 'CONFIRMADO'];

  get(termoBusca?: string | undefined): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: Atendimento[]) => {
        this.registros = resposta.filter(item => {
          return this.status.includes(item.status);
        });
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja cancelar o agendamento?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
        }
      });
    }
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

}
