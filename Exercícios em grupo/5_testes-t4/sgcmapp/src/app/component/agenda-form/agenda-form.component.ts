import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Atendimento } from 'src/app/model/atendimento';
import { Convenio } from 'src/app/model/convenio';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Paciente } from 'src/app/model/paciente';
import { Profissional } from 'src/app/model/profissional';
import { AlertaService } from 'src/app/service/alerta.service';
import { AtendimentoService } from 'src/app/service/atendimento.service';
import { ConvenioService } from 'src/app/service/convenio.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { Utils } from 'src/app/utils/utils';
import { IForm } from '../i-form';

@Component({
  selector: 'app-agenda-form',
  templateUrl: './agenda-form.component.html',
  styles: [
  ]
})
export class AgendaFormComponent implements IForm<Atendimento>, OnInit {

  constructor (
    private servico: AtendimentoService,
    private servicoConvenio: ConvenioService,
    private servicoPaciente: PacienteService,
    private servicoProfisisonal: ProfissionalService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {

    this.servicoConvenio.get().subscribe({
      next: (resposta: Convenio[]) => {
        this.convenios = resposta.filter(
          item => item.ativo == true
        ).sort(
          (a, b) => a.nome.localeCompare(b.nome)
        );
      }
    });

    this.servicoPaciente.get().subscribe({
      next: (resposta: Paciente[]) => {
        this.pacientes = resposta.sort(
          (a, b) => a.nome.localeCompare(b.nome)
        );
      }
    });

    this.servicoProfisisonal.get().subscribe({
      next: (resposta: Profissional[]) => {
        this.profissionais = resposta.sort(
          (a, b) => a.nome.localeCompare(b.nome)
        );
      }
    });

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Atendimento) => {
          this.registro = resposta;
        },
        complete: () => {
          this.verificaHorario();
        }
      });
    }
    
  }

  registro: Atendimento = <Atendimento>{};
  profissionais: Profissional[] = Array<Profissional>();
  convenios: Convenio[] = Array<Convenio>();
  pacientes: Paciente[] = Array<Paciente>();
  compareById = Utils.compareById;
  horariosOcupados: string[] = Array<string>();

  save(): void {
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/agenda']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso."
        });
      }
    });
  }

  verificaHorario(): void {
    let profissional_id = this.registro.profissional.id;
    let data = this.registro.data;
    if (profissional_id && data) {
      this.servico.getHorariosOcupados(profissional_id, data).subscribe({
        next: (resposta: string[]) => {
          this.horariosOcupados = resposta;
        },
        complete: () => {
          const selectHora = document.querySelector<HTMLSelectElement>('form select[name="hora"]');
          if (selectHora) {
            Array.from(selectHora.options).forEach(opcao => {
              opcao.disabled = this.horariosOcupados.includes(opcao.value)
                && opcao.value != this.registro.hora;
            });
          }
        }
      });
    }
  }

}
