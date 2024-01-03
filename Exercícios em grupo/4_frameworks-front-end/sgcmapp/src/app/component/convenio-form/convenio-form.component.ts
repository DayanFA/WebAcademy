import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Convenio } from 'src/app/model/convenio';
import { ConvenioService } from 'src/app/service/convenio.service';
import { AlertaService } from 'src/app/service/alerta.service';
import { ETipoAlerta } from 'src/app/model/e-tipo-alerta';
import { Utils } from 'src/app/utils/utils';

@Component({
  selector: 'app-convenio-form',
  templateUrl: './convenio-form.component.html',
  styleUrls: [
  ]
})
export class ConvenioFormComponent implements OnInit {

  constructor(
    private servico: ConvenioService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoAlerta: AlertaService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Convenio) => {
          this.registro = resposta;
        }
      });
    }

    this.servico.get().subscribe({
      next: (resposta: Convenio[]) => {
        this.registros = resposta;
      }
    });
  }

  registro: Convenio = <Convenio>{};
  registros: Convenio[] = [];
  //papelAtivo: string | null = null;
  compareById = Utils.compareById;

  save(form: NgForm): void {
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/convenios/form']);
        this.servicoAlerta.enviarAlerta({
          tipo: ETipoAlerta.SUCESSO,
          mensagem: "Operação realizada com sucesso."
        });
      }
    });
  }

}