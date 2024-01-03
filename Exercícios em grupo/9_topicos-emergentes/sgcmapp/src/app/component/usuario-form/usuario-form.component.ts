import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Usuario } from 'src/app/model/usuario';
import { AlertaService } from 'src/app/service/alerta.service';
import { UsuarioService } from 'src/app/service/usuario.service';
import { IForm } from '../i-form';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styles: [
  ]
})
export class UsuarioFormComponent implements OnInit, IForm<Usuario> {

  constructor(
      private servico: UsuarioService,
      private servicoAlerta: AlertaService,
      private router: Router,
      private route: ActivatedRoute) { }

  ngOnInit(): void {

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Usuario) => {
          this.registro = resposta;
        }
      });
    }

  }

  registro: Usuario = <Usuario>{};
  
  save(): void {
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/config/usuarios']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso."
        });
      }
    });
  }

}
