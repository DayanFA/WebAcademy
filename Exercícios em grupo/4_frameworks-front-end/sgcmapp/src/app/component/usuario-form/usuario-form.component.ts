import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/service/usuario.service';
import { AlertaService } from 'src/app/service/alerta.service';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Utils } from 'src/app/utils/utils';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: [
  ]
})
export class UsuarioFormComponent implements OnInit {

  constructor(
    private servico: UsuarioService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Usuario) => {
          this.registro = resposta;
        }
      });
    }

    this.servico.get().subscribe({
    next: (resposta: Usuario[]) => {
    this.papeis = resposta.map(usuario => usuario.papel)
                          .filter((papel, index, array) => array.indexOf(papel) === index);
    if (this.registro.papel) {
      this.papelAtivo = this.registro.papel;
    }
    }
    });
  }

  registro: Usuario = <Usuario>{};
  papeis: string[] = [];
  papelAtivo: string | null = null;
  compareById = Utils.compareById;

  save(form: NgForm): void {
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/usuario']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso."
        });
      }
    });
  }

}