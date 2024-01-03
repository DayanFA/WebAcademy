import { Component } from '@angular/core';

@Component({
  selector: 'app-select-tema',
  templateUrl: './select-tema.component.html',
  styleUrls: ['./select-tema.component.scss']
})
export class SelectTemaComponent {
  url: string = '';
  linkTema: HTMLLinkElement | null = null;
  selectTema: HTMLSelectElement | null = null;

  ngOnInit() {
    this.linkTema = document.querySelector("#link-tema");
    this.selectTema = document.querySelector("select#tema");
    
  // Altera o tema o arquivo CSS de acordo com o tema selecionado
    const mudarTema = (temaSelecionado: string) => {
      this.url = "/assets/css/estilo-tema-" + temaSelecionado + ".css";
      if (this.linkTema)
        this.linkTema.href = this.url;

        console.log (this.linkTema)
    }

    //window.onload = () => {
      // Altera o tema quando mudar a opção selecionada
      //this.selectTema = document.querySelector("select#tema");
      if (this.selectTema) 
        this.selectTema.addEventListener("change", evento => {
            const temaSelecionado = (evento.target as HTMLSelectElement).value;
            if (temaSelecionado) {
                mudarTema(temaSelecionado);
                // Salva a opção de tema escolhida pelo usuário no localStorage
                localStorage.setItem("tema", temaSelecionado);
            }
        });

      // Recupera a opção de tema escolhida pelo usuário e
      // altera o tema se houver uma opção salva no localStorage
      let tema = localStorage.getItem("tema");
      if (tema) {
          mudarTema(tema);
          // Seleciona a opção de tema escolhida pelo usuário
          if (this.selectTema)
            this.selectTema.value = tema;
      }
    //}
  }
}
