import { Component, OnInit } from '@angular/core';
import { IList } from '../i-list';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styles: [
  ]
})
export class UsuarioListComponent implements IList<Usuario>, OnInit {

  constructor(private servico: UsuarioService) {}

  ngOnInit(): void {
    this.get();
  }

  registros: Usuario[] = Array<Usuario>();

  get(termoBusca?: string | undefined): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: Usuario[]) => {
        this.registros = resposta;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja excluir o usuário?')) {
      this.servico.delete(id).subscribe({
        complete: () => {
          this.get();
        }
      });
    }
  }


}