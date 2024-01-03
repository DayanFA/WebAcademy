// navigation.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NavigationService {
  private activeLinkSubject = new BehaviorSubject<string>('');
  private activeSubMenuLinkSubject = new BehaviorSubject<string>('');

  setActiveLink(link: string) {
    this.activeLinkSubject.next(link);
  }

  getActiveLink() {
    return this.activeLinkSubject.asObservable();
  }

  setActiveSubMenuLink(link: string) {
    this.activeSubMenuLinkSubject.next(link);
  }

  getActiveSubMenuLink(): Observable<string> {
    return this.activeSubMenuLinkSubject.asObservable();
  }
}
