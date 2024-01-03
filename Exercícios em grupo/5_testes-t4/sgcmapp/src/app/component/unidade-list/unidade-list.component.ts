import { Component, OnInit } from '@angular/core';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
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
    this.get();
  }

  registros: Unidade[] = Array<Unidade>();

  get(termoBusca?: string): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: Unidade[]) => {
        this.registros = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir a unidade?')) {
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
