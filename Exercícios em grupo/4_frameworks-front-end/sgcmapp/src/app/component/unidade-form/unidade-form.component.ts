import { Component, OnInit } from '@angular/core';
import { IForm } from '../i-form';
import { Unidade } from 'src/app/model/unidade';
import { Convenio } from 'src/app/model/convenio';
import { Paciente } from 'src/app/model/paciente';
import { NgForm } from '@angular/forms';
import { UnidadeService } from 'src/app/service/unidade.service';
import { ConvenioService } from 'src/app/service/convenio.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { subscribeOn } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Utils } from 'src/app/utils/utils';
import { AlertaService } from 'src/app/service/alerta.service';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';

@Component({
  selector: 'app-unidade-form',
  templateUrl: './unidade-form.component.html',
  styles: [
  ]
})
export class UnidadeFormComponent implements IForm<Unidade>, OnInit {

  constructor (
    private servico: UnidadeService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Unidade) => {
          this.registro = resposta;
        }
      })
    }
    
  }

  registro: Unidade = <Unidade>{};
  compareById = Utils.compareById;

  save(form: NgForm): void {
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/unidade']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso."
        });
      }
    })
  }

}