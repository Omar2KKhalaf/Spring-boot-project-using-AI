import { Component, HostListener, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { LanguageService } from '../../services/language/language.service';
import { UserService } from '../../services/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isAuthenticated: boolean = false; // Set properly based on your authentication logic
  currentLanguage;
  langSubscription;
  isInvisible: boolean = false;

  constructor(private translate: TranslateService, private languageService: LanguageService, private userService: UserService, private router: Router) {
    this.langSubscription = this.languageService.language$.subscribe(lang => {
      this.currentLanguage = lang;
    });
  }

  toggleNavMenu() {
    this.isInvisible = !this.isInvisible;
  }

  ngOnInit(): void {
    this.translate.setDefaultLang('en');
    if (window.innerWidth <= 768) {
      this.isInvisible = true;
    }
    this.isAuthenticated = this.userService.isAuthenticated();
  }

  switchLanguage() {
    this.languageService.changeLanguage(); 
  }

  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }

  logout(): void {
    this.userService.clearUser();
    this.router.navigate(['/login']);
  }
}