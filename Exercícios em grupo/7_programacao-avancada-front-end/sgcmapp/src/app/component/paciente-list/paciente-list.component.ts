import { Component, OnInit } from '@angular/core';
import { Paciente } from 'src/app/model/paciente';
import { AlertaService } from 'src/app/service/alerta.service';
import { PacienteService } from 'src/app/service/paciente.service';
import { IList } from '../i-list';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { PageRequest } from '../../model/page-request';
import { PageResponse } from '../../model/page-response';

@Component({
  selector: 'app-paciente-list',
  templateUrl: './paciente-list.component.html',
  styles: [
  ]
})
export class PacienteListComponent implements OnInit, IList<Paciente> {

  constructor(
    private servico: PacienteService,
    private servicoAlerta: AlertaService
  ) { }

  ngOnInit(): void {
    this.get();
  }

  registros: Paciente[] = Array<Paciente>();

  get(termoBusca?: string): void {
    this.servico.get(termoBusca).subscribe({
      next: (resposta: PageResponse<Paciente>) => {
        this.registros = resposta.content;
      }
    });
  }

  delete(id: number): void {
    if (confirm('Deseja realmente excluir o paciente?')) {
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
