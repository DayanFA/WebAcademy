import { Component } from '@angular/core';
import { LoaderService } from 'src/app/service/loader.service';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss']
})
export class LoaderComponent {

  loading: boolean = false;

  constructor(private servico: LoaderService) {
    this.servico.isLoading.subscribe(valor => {
      this.loading = valor;
    });
  }

}
