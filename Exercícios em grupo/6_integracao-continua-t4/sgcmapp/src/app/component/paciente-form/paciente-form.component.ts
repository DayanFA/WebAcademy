import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Paciente } from 'src/app/model/paciente';
import { AlertaService } from 'src/app/service/alerta.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { IForm } from '../i-form';

@Component({
  selector: 'app-paciente-form',
  templateUrl: './paciente-form.component.html',
  styles: [
  ]
})
export class PacienteFormComponent implements OnInit, IForm<Paciente> {

  constructor(
      private servico: PacienteService,
      private servicoAlerta: AlertaService,
      private router: Router,
      private route: ActivatedRoute) { }

  ngOnInit(): void {

    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Paciente) => {
          this.registro = resposta;
        }
      });
    }

  }

  registro: Paciente = <Paciente>{};
  
  save(): void {
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/pacientes']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso."
        });
      }
    });
  }

}
