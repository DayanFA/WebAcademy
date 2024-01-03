import { Component, OnInit } from '@angular/core';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Especialidade } from 'src/app/model/especialidade';
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
    this.get();
  }

  registros: Especialidade[] = Array<Especialidade>();

  get(termoBusca?: string): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: Especialidade[]) => {
        this.registros = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir a especialidade?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
          this.servicoAlerta.enviarAlerta({
            tipo: ETipoAlerta.SUCESSO,
            mensagem: "Operação realizada com sucesso."
          });
        }
      });
    }
  }

}
