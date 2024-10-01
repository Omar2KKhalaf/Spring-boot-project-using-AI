import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userSubject: BehaviorSubject<User | null>;

  constructor() {
    // Attempt to fetch cached user (including role and jwt) from localStorage
    const cachedUser = localStorage.getItem('currentUser');
    console.log(cachedUser);
    this.userSubject = new BehaviorSubject<User | null>(cachedUser ? JSON.parse(cachedUser) : null);
  }

  getCurrentUser(): User | null {
    return this.userSubject.value;
  }

  isAuthenticated(): boolean {
    // Implement logic, e.g., check if a valid JWT token exists
    if(!this.userSubject.value){
      return false;
    }
    const token =this.userSubject.value.jwt // or localStorage
    return !!token; // Simple existence check
  }

  public get currentUser$() {
    return this.userSubject.asObservable();
  }

  // Method to update and cache user details, including role and jwt
  public updateUser(user: User) {
    localStorage.setItem('currentUser', JSON.stringify(user));
    this.userSubject.next(user);
  }

  // Method to clear user details from cache (e.g., during logout)
  public clearUser() {
    localStorage.removeItem('currentUser');
    this.userSubject.next(null);
  }
}
