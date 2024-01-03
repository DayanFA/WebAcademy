import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Especialidade } from 'src/app/model/especialidade';
import { Profissional } from 'src/app/model/profissional';
import { Unidade } from 'src/app/model/unidade';
import { AlertaService } from 'src/app/service/alerta.service';
import { EspecialidadeService } from 'src/app/service/especialidade.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { UnidadeService } from 'src/app/service/unidade.service';
import { Utils } from 'src/app/utils/utils';
import { IForm } from '../i-form';

@Component({
  selector: 'app-profissional-form',
  templateUrl: './profissional-form.component.html',
  styles: [
  ]
})
export class ProfissionalFormComponent implements OnInit, IForm<Profissional> {

  constructor(
      private servico: ProfissionalService,
      private servicoAlerta: AlertaService,
      private servicoEspecialidade: EspecialidadeService,
      private servicoUnidade: UnidadeService,
      private router: Router,
      private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.servicoEspecialidade.get().subscribe({
      next: (resposta: Especialidade[]) => {
        this.especialidades = resposta;
      }
    });

    this.servicoUnidade.get().subscribe({
      next: (resposta: Unidade[]) => {
        this.unidades = resposta;
      }
    });

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Profissional) => {
          this.registro = resposta;
        }
      });
    }

  }

  registro: Profissional = <Profissional>{};
  especialidades: Especialidade[] = Array<Especialidade>();
  unidades: Unidade[] = Array<Unidade>();
  compareById = Utils.compareById;
  
  save(): void {
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/profissionais']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso."
        });
      }
    });
  }

}
