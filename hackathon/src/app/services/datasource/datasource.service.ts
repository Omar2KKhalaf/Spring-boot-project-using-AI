import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { BehaviorSubject, Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { UserService } from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class DatasourceService {
  private baseUrl = 'http://localhost:8086/api';
  public isLoading: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);


  constructor(private http: HttpClient, private _snackBar: MatSnackBar,private userService:UserService) { }

  /**
   * Makes an HTTP request based on provided parameters.
   * 
   * @param method - The HTTP method ('GET', 'POST', 'PUT', 'DELETE')
   * @param subUrl - The endpoint excluding the base URL
   * @param body - (Optional) The request payload for methods like 'POST' or 'PUT'
   * @param headers - (Optional) Additional headers for the request
   * @returns Observable<any> - The response from the backend
   */
  public makeRequest(method: 'GET' | 'POST' | 'PUT' | 'DELETE', 
                     subUrl: string, 
                     body?: any, 
                     headers?: {[header: string]: string | string[]}): Observable<any> {
    const url = `${this.baseUrl}/${subUrl}`;
    this.isLoading.next(true); // Set loading to true before the request

    // Setting up headers
    let httpHeaders = new HttpHeaders();
    if (headers) {
      for (const key of Object.keys(headers)) {
        httpHeaders = httpHeaders.append(key, headers[key]);
      }
    }
    if(this.userService.isAuthenticated()){
      httpHeaders = httpHeaders.append('token', this.userService.getCurrentUser().jwt);
    }

    // Determine method and return appropriate observable
    switch(method) {
      case 'GET':
        return this.http.get(url, { headers: httpHeaders }).pipe(
          tap(() => { // Success side effect
            this.handleSuccess();
          }),
          catchError(this.handleError.bind(this))
        );
      case 'POST':
        return this.http.post(url, body, { headers: httpHeaders }).pipe(
          tap(() => { // Success side effect
            this.handleSuccess();
          }),
          catchError(this.handleError.bind(this))
        );
      case 'PUT':
        return this.http.put(url, body, { headers: httpHeaders }).pipe(
          tap(() => { // Success side effect
            this.handleSuccess();
          }),
          catchError(this.handleError.bind(this))
        );
      case 'DELETE':
        return this.http.delete(url, { headers: httpHeaders }).pipe(
          tap(() => { // Success side effect
            this.handleSuccess();
          }),
          catchError(this.handleError.bind(this))
        );
      default:
        throw new Error('Unsupported method'); // For handling unsupported methods
    }
  }

  handleSuccess(){
    console.log("test");
    this.isLoading.next(false); // Set loading to false after the request completes

    this._snackBar.open('Successful Operation', 'Close', {
      duration: 3000,
    }); 
  }
  handleError(error: any) {
    this.isLoading.next(false); // Set loading to false after the request completes

    this._snackBar.open('An error occurred: ' + error.error.errorMessage, 'Close', {
      duration: 3000,
    }); 
    return throwError(error);
  }
}
