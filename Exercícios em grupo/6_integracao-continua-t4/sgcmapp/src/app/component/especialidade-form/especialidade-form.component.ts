import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Especialidade } from 'src/app/model/especialidade';
import { AlertaService } from 'src/app/service/alerta.service';
import { EspecialidadeService } from 'src/app/service/especialidade.service';
import { IForm } from '../i-form';

@Component({
  selector: 'app-especialidade-form',
  templateUrl: './especialidade-form.component.html',
  styles: [
  ]
})
export class EspecialidadeFormComponent implements OnInit, IForm<Especialidade> {

  constructor(
      private servico: EspecialidadeService,
      private servicoAlerta: AlertaService,
      private router: Router,
      private route: ActivatedRoute) { }

  ngOnInit(): void {

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Especialidade) => {
          this.registro = resposta;
        }
      });
    }

  }

  registro: Especialidade = <Especialidade>{};
  
  save(): void {
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/config/especialidades']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso."
        });
      }
    });
  }

}
