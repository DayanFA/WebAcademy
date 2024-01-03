import { Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { Alerta } from 'src/app/model/alerta';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { AlertaService } from 'src/app/service/alerta.service';

@Component({
  selector: 'app-alerta',
  templateUrl: './alerta.component.html',
  styleUrls: ['./alerta.component.css']
})
export class AlertaComponent implements OnInit {

  constructor(
    private servico: AlertaService,
    private router: Router
    ) {}

  ngOnInit(): void {

    this.servico.receberAlerta().subscribe({
      next: (alerta: Alerta) => {
        this.exibirAlerta(alerta);
      }
    });

    this.router.events.subscribe({
      next: (evento) => {
        if (evento instanceof NavigationStart) {
          this.fecharAlerta();
        }
      }
    });

  }

  exibirAlerta(alerta: Alerta): void {
    const elementoAlerta = document.querySelector<HTMLElement>('div.alerta');
    const elementoMensagem = document.querySelector<HTMLElement>('div.alerta span#mensagem');
    if (elementoAlerta && elementoMensagem) {
      elementoMensagem.innerText = alerta.mensagem;
      elementoAlerta.classList.add(alerta.tipo);
      elementoAlerta.classList.remove('inativo');
    }
  }

  fecharAlerta(): void {
    const elementoAlerta = document.querySelector<HTMLElement>('div.alerta');
    if (elementoAlerta) {
      elementoAlerta.classList.add('inativo');
      elementoAlerta.classList.remove(
        ETipoAlerta.SUCESSO,
        ETipoAlerta.ERRO
      );
    }
  }

}
