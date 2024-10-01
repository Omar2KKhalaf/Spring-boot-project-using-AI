import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { LanguageService } from '../../services/language/language.service';
import { emailValidator, passwordValidator } from '../../validators/login.validator';
import { Data, Router } from '@angular/router';
import { DatasourceService } from 'src/app/services/datasource/datasource.service';
import { UserService } from 'src/app/services/user/user.service';
import { User } from 'src/app/model/user.model';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  currentLanguage;
  langSubscription;

  constructor(private fb: FormBuilder, private translate: TranslateService, private languageService: LanguageService,
    private dataSourceService: DatasourceService, private userService: UserService, private _snackBar: MatSnackBar, private router: Router) {
    this.langSubscription = this.languageService.language$.subscribe(lang => {
      this.currentLanguage = lang;
    });
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, emailValidator(this.translate)]],
      password: ['', [Validators.required, passwordValidator(this.translate)]]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.registerForm.invalid) {
      this.markFormGroupTouched(this.registerForm);
      return;
    }
    this.dataSourceService.makeRequest('POST', 'auth/register', { email: this.registerForm.get('email').value, password: this.registerForm.get('password').value }).subscribe(data => {
      this.router.navigate(['/login']);
    });
  }



  private markFormGroupTouched(formGroup: FormGroup) {
    (<any>Object).values(formGroup.controls).forEach(control => {
      control.markAsTouched();

      if (control.controls) { // in case of nested FormGroups
        this.markFormGroupTouched(control);
      }
    });
    Object.keys(this.registerForm.controls).forEach(field => {
      const control = this.registerForm.get(field);
      control.setValue(control.value);
    });
  }


  switchLanguage() {
    this.languageService.changeLanguage();
  }

  ngOnDestroy() {
    this.langSubscription.unsubscribe();
  }
}