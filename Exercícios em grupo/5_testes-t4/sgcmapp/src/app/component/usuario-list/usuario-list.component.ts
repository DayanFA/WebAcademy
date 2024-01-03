import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/model/usuario';
import { AlertaService } from 'src/app/service/alerta.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import { IList } from '../i-list';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styles: [
  ]
})
export class UsuarioListComponent implements OnInit, IList<Usuario> {

  constructor(
    private servico: UsuarioService,
    private servicoAlerta: AlertaService
  ) { }

  ngOnInit(): void {
    this.get();
  }

  registros: Usuario[] = Array<Usuario>();

  get(termoBusca?: string): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: Usuario[]) => {
        this.registros = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir o usuário?')) {
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
