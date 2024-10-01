import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './components/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HackathonDetailsComponent } from './components/hackathon-details/hackathon-details.component';
import { MatButtonModule, MatCardModule, MatChipList, MatChipsModule, MatDatepickerModule, MatDialogModule, MatFormFieldModule, MatIconModule, MatInputModule, MatNativeDateModule, MatSelectModule, MatSnackBarModule, MatTableModule, MatToolbarModule } from '@angular/material';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { AddHackathonDialogComponent } from './components/add-hackathon-dialog/add-hackathon-dialog.component';
import { UserComponent } from './components/user-dashboard/user/user.component';
import { RegisterHackathonComponent } from './components/user-dashboard/register-hackathon/register-hackathon.component';

import { LoadingComponent } from './loading/loading.component';

// Function to load translations files
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AdminDashboardComponent,
    HackathonDetailsComponent,
    HeaderComponent,
    FooterComponent,
    AddHackathonDialogComponent,
    LoadingComponent,
    UserComponent,
    RegisterHackathonComponent,
    LoadingComponent
  ],
  imports: [
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    // Mat labels, inputs, buttons, and more
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatCardModule,
    MatToolbarModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    //mat-icon
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatTableModule,
    MatSelectModule,
    MatChipsModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [AddHackathonDialogComponent]
})
export class AppModule { }
