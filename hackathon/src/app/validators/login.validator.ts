import { AbstractControl, ValidationErrors } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';

export function emailValidator(translate: TranslateService): any {
  return (control: AbstractControl): ValidationErrors | null => {
    const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/i;

    if (!control.dirty && control.untouched) {
      return null; // No validation error if field is empty
    }

    if (!emailPattern.test(control.value)) {
      return { invalidEmail: translate.instant('INVALID_EMAIL') };
    }

    return null;
  };
}

export function passwordValidator(translate: TranslateService): any {
  return (control: AbstractControl): ValidationErrors | null => {
    const passwordPattern = /^[A-Za-z\d@$!%*?&]{5,}$/;

    if (!control.dirty && control.untouched) {
      return null; // No validation error if field is empty
    }

    if (!passwordPattern.test(control.value)) {
      return { invalidPassword: translate.instant('INVALID_PASSWORD') };
    }

    return null;
  };
}