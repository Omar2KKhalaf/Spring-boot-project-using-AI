import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { emailValidator, passwordValidator } from '../../validators/login.validator';
import { TranslateService } from '@ngx-translate/core';
import { LanguageService } from '../../services/language/language.service';
import { User } from 'src/app/model/user.model';
import { UserService } from 'src/app/services/user/user.service';
import { DatasourceService } from 'src/app/services/datasource/datasource.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  currentLanguage;
  langSubscription;

  constructor(private fb: FormBuilder, private translate: TranslateService, private languageService: LanguageService
    , private userService: UserService, private dataSourceService: DatasourceService,
    private router:Router) {
    this.langSubscription = this.languageService.language$.subscribe(lang => {
      this.currentLanguage = lang;
    });
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, emailValidator(this.translate)]],
      password: ['', [Validators.required, passwordValidator(this.translate)]]
    });
  }

  ngOnInit(): void {

  }
  onSubmit() {
    if (this.loginForm.invalid) {
      this.markFormGroupTouched(this.loginForm);

      return;
    }
    this.dataSourceService.makeRequest('POST', 'auth/login', { email: this.loginForm.get('email').value, password: this.loginForm.get('password').value }).subscribe(data => {
      this.userService.updateUser(new User(null, 'test', this.loginForm.get('email').value, data.role, data.token));
      console.log(data.role);
      console.log(data);
      // Route user based on their role
      if (data.role === 'ADMIN') {
        this.router.navigate(['/admin-dashboard']);
      } else if (data.role === 'USER') {
        this.router.navigate(['/user-dashboard']);
      }
    });
  }

  private markFormGroupTouched(formGroup: FormGroup) {
    (<any>Object).values(formGroup.controls).forEach(control => {
      control.markAsTouched();

      if (control.controls) { // in case of nested FormGroups
        this.markFormGroupTouched(control);
      }
    });
    Object.keys(this.loginForm.controls).forEach(field => {
      const control = this.loginForm.get(field);
      control.setValue(control.value);
    });
  }

  switchLanguage() {
    this.languageService.changeLanguage(); // Use the shared service to change the language
  }

  ngOnDestroy() {
    // Unsubscribe to ensure no memory leaks
    this.langSubscription.unsubscribe();
  }
}
