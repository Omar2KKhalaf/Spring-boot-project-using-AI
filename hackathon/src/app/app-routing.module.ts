import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { RedirectIfAuthenticatedGuard } from './services/auth/redirect-if-authenticated.guard';
import { AdminGuard } from './services/auth/admin.guard';
import { UserComponent } from './components/user-dashboard/user/user.component';
import { UserGuard } from './services/auth/user.guard';
import { RegisterHackathonComponent } from './components/user-dashboard/register-hackathon/register-hackathon.component';
import { HackathonDetailsComponent } from './components/hackathon-details/hackathon-details.component';
import { LoadingComponent } from './loading/loading.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Set default route to LoginComponent
  { path: 'login', component: LoginComponent, canActivate: [RedirectIfAuthenticatedGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [RedirectIfAuthenticatedGuard] },
  { path: 'user-dashboard', component:UserComponent, canActivate: [UserGuard] },
  { path: 'register-hackathon/:id', component:RegisterHackathonComponent, canActivate: [UserGuard] },
  { path: 'admin-dashboard', component: AdminDashboardComponent, canActivate: [AdminGuard] },
  { path: 'hackathon-details/:id', component: HackathonDetailsComponent, canActivate: [AdminGuard] },
  { path: 'loading', component: LoadingComponent},

  // Other routes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
