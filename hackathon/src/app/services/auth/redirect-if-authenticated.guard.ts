import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class RedirectIfAuthenticatedGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) {}

  canActivate(): boolean {
    if (this.userService.isAuthenticated()) {
      const user = this.userService.getCurrentUser(); // Assume this method fetches the current user
      
      // Redirect based on role
      if (user.role === 'ADMIN') {
        this.router.navigate(['/admin-dashboard']);
      } else if (user.role === 'USER') {
        this.router.navigate(['/user-dashboard']);
      }
      return false;
    }
    return true;
  }
}