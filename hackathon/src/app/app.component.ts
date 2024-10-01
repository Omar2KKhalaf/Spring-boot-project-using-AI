import { DOCUMENT } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { LanguageService } from './services/language/language.service';
import { MatSnackBar } from '@angular/material';
import { DatasourceService } from './services/datasource/datasource.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoading=false;
  constructor(private translate: TranslateService, @Inject(DOCUMENT) private document: Document , private languageService: LanguageService,
  private _snackBar:MatSnackBar,private dataSourceService:DatasourceService) {
    // Subscribe to language changes
    this.languageService.language$.subscribe(lang => this.switchLanguage(lang));
    this.dataSourceService.isLoading.subscribe((loading) => {
      this.isLoading = loading;
    });
  }

  switchLanguage(language: string) {
    this.translate.use(language);
    this.adjustDirAttribute(language);
  }

  private adjustDirAttribute(language: string) {
    if (language === 'ar') {
      this.document.documentElement.dir = 'rtl';
    } else {
      this.document.documentElement.dir = 'ltr';
    }
  }
}
