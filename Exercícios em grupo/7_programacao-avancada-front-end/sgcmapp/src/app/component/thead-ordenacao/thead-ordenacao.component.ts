import { trigger } from '@angular/animations';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: '[app-thead-ordenacao]',
  templateUrl: './thead-ordenacao.component.html',
  styles: [
  ]
})
export class TheadOrdenacaoComponent {

  @Input() colunas: { campo: string, descricao: string }[] = [];
  @Output() eventoOrdenacao = new EventEmitter<string[]>();
  ordenacao: string[] = [];

  ordenar(campo: string): void {

    if (campo) {
      if (this.ordenacao.includes(campo)) {
        this.ordenacao = this.ordenacao.map(item => item == campo ? campo + ',desc' : item);
      } else if (this.ordenacao.includes(campo + ',desc')) {
        this.ordenacao = this.ordenacao.filter(item => item != campo + ',desc');
      } else {
        this.ordenacao.push(campo);
      }
      this.eventoOrdenacao.emit(this.ordenacao);
    }

  }

  classeAtivo(campo: string): string {
    let classe = '';
    if (this.ordenacao.length > 0) {
      if (this.ordenacao.includes(campo)) {
        classe = 'ativo-asc';
      } else if (this.ordenacao.includes(campo + ',desc')) {
        classe = 'ativo-desc';
      }
    }
    return classe;
  }

}
