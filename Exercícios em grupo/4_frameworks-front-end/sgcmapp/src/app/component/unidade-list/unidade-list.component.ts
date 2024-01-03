import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Unidade } from 'src/app/model/unidade';
import { UnidadeService } from 'src/app/service/unidade.service';

@Component({
  selector: 'app-unidade-list',
  templateUrl: './unidade-list.component.html',
  styles: [
  ]
})
export class UnidadeListComponent implements IList<Unidade>, OnInit {

  constructor(private servico: UnidadeService) {}

  ngOnInit(): void {
    this.get();
  }

  registros: Unidade[] = Array<Unidade>();

  get(termoBusca?: string | undefined): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: Unidade[]) => {
        this.registros = resposta
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja excluir a unidade?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
        }
      });
    }
  }



}