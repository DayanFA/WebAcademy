// navigation.component.ts
import { Component, OnInit } from '@angular/core';
import { NavigationService } from '../../service/navigation.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html'
})
// ... (seu código existente)

export class NavigationComponent implements OnInit {
  activeLink: string = ''; // Rota ativa no menu principal
  activeSubMenuLink: string = ''; // Rota ativa no submenu "Configurações"

  constructor(private navigationService: NavigationService) {}

  ngOnInit() {
    this.navigationService.getActiveLink().subscribe((link) => {
      this.activeLink = link;
    });
  }

  onLinkClick(link: string) {
    this.navigationService.setActiveLink(link); // Limpar a rota ativa no submenu ao clicar em um link principal
  }

  onSubMenuLinkClick(link: string) {
    this.navigationService.setActiveSubMenuLink(link);
  }
}

