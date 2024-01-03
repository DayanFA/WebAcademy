import { Component, OnInit } from '@angular/core';
import { IForm } from '../i-form';
import { Atendimento } from 'src/app/model/atendimento';
import { Profissional } from 'src/app/model/profisisonal';
import { Convenio } from 'src/app/model/convenio';
import { Paciente } from 'src/app/model/paciente';
import { NgForm } from '@angular/forms';
import { AtendimentoService } from 'src/app/service/atendimento.service';
import { ConvenioService } from 'src/app/service/convenio.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { ProfissionalService } from 'src/app/service/profissional.service';
import { subscribeOn } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Utils } from 'src/app/utils/utils';
import { AlertaService } from 'src/app/service/alerta.service';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';

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
          (convenio: Convenio) => convenio.ativo
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
        }
      })
    }
    
  }

  registro: Atendimento = <Atendimento>{};
  profissionais: Profissional[] = Array<Profissional>();
  convenios: Convenio[] = Array<Convenio>();
  pacientes: Paciente[] = Array<Paciente>();
  horariosDisponiveis: string[] = [
    '14:00:00', '14:30:00', '15:00:00', '15:30:00',
    '16:00:00', '16:30:00', '17:00:00', '17:30:00',
    '18:00:00', '18:30:00', '19:00:00', '19:30:00',
    '20:00:00'
  ];
  horariosOcupados: string[] = [];
  compareById = Utils.compareById;

  SelectGetHorarios() {
    if (this.registro.profissional && this.registro.data) {
      this.servico.getHorarios(this.registro.profissional.id, this.registro.data).subscribe({
        next: (horarios: string[]) => {
          this.horariosOcupados = horarios;
        }
      })
    }
  }

  save(form: NgForm): void {
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/agenda']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso."
        });
      }
    })
  }
  
}
