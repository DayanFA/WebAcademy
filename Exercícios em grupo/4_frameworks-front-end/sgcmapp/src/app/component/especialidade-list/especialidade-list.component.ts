import { Component, OnInit } from '@angular/core';
import { Especialidade } from 'src/app/model/especialidade';
import { EspecialidadeService } from 'src/app/service/especialidade.service';
import { IList } from '../i-list';

@Component({
  selector: 'app-especialidade-list',
  templateUrl: './especialidade-list.component.html',
  styleUrls: ['./especialidade-list.component.css']
})
export class EspecialidadeListComponent implements IList<Especialidade>, OnInit {

  constructor(private servico: EspecialidadeService) {}

  ngOnInit(): void {
    this.get();
  }

  registros: Especialidade[] = Array<Especialidade>();

  get(termoBusca?: string | undefined): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: Especialidade[]) => {
        this.registros = resposta;
      },
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

}
