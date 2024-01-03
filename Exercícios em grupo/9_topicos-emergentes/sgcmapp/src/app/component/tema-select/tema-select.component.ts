import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tema-select',
  templateUrl: './tema-select.component.html',
  styles: [
  ]
})
export class TemaSelectComponent implements OnInit {

  temaSelecionado: string = '';

  ngOnInit(): void {
    this.temaSelecionado = localStorage.getItem('tema') || '';
    if (this.temaSelecionado) {
      this.mudarTema();
    }
  }

  mudarTema(): void {
    let url = "/assets/css/estilo-tema-" + this.temaSelecionado + ".css";
    let linkTema = document.querySelector<HTMLLinkElement>("#link-tema");
    if (linkTema) {
      linkTema.href = url;
    }
    localStorage.setItem('tema', this.temaSelecionado);
  }

}
