import { Component, OnInit } from '@angular/core';
import { Convenio } from 'src/app/model/convenio';
import { AlertaService } from 'src/app/service/alerta.service';
import { ConvenioService } from 'src/app/service/convenio.service';
import { IList } from '../i-list';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';

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
    this.get();
  }

  registros: Convenio[] = Array<Convenio>();

  get(termoBusca?: string): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: Convenio[]) => {
        this.registros = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir o convênio?')) {
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
