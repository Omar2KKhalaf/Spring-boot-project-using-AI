import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LanguageService {
  // A Subject to hold the current language; initializes with 'en'
  private languageSubject: BehaviorSubject<string>;


  constructor() { 
    const cachedLang = localStorage.getItem('appLanguage') || 'en';
    this.languageSubject = new BehaviorSubject<string>(cachedLang);

  }

  public get language$() {
    return this.languageSubject.asObservable();
  }

  // Method to change and cache the language
  public changeLanguage() {
    const newLang=this.languageSubject.value === 'en' ? 'ar' : 'en';
    localStorage.setItem('appLanguage', newLang);
    this.languageSubject.next(newLang);
  }
}
