import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { NavigationService } from './service/navigation.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'SGCM';
  currentUrl: string = '';
  activeLink: string = '';
  activeSubMenuLink: string = '';

  constructor(router: Router, private navigationService: NavigationService) {
    router.events.subscribe((evento) => {
      if (evento instanceof NavigationEnd) {
        if (evento.url !== '') {
          this.currentUrl = evento.url;
        } else {
          this.currentUrl = '';
        }

        // Atualize a rota ativa no serviço para manter a sincronização
        this.navigationService.setActiveLink(this.currentUrl);
      }
    });
  }

  onLinkClick(link: string) {
    this.navigationService.getActiveLink().subscribe((link) => {
      this.activeLink = link;
    });
  }

  onSubMenuLinkClick(link: string) {
    this.navigationService.getActiveSubMenuLink().subscribe((subLink) => {
      this.activeSubMenuLink = subLink;
    });
  }
}
