import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Convenio } from 'src/app/model/convenio';
import { ConvenioService } from 'src/app/service/convenio.service';

@Component({
  selector: 'app-convenio-list',
  templateUrl: './convenio-list.component.html',
  styles: [
  ]
})
export class ConvenioListComponent implements IList<Convenio>, OnInit {

  constructor(private servico: ConvenioService) {}

  ngOnInit(): void {
    this.get();
  }

  registros: Convenio[] = Array<Convenio>();

  get(termoBusca?: string | undefined): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: Convenio[]) => {
        this.registros = resposta
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja excluir o convênio?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
        }
      });
    }
  }


}
