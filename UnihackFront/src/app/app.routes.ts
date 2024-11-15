import { Routes } from '@angular/router';
import {AuthenticationComponent} from './components/authentication/authentication.component';
import {ClientDashboardComponent} from './components/pages/client-dashboard/client-dashboard.component';
import {AuthGuard} from './components/authentication/guard/auth-guard.service';
import {DoctorDashboardComponent} from './components/pages/doctor-dashboard/doctor-dashboard.component';

export const routes: Routes = [
    { path: '', component: AuthenticationComponent },
    { path: 'login', component: AuthenticationComponent },
    { path: 'dashboard', component: ClientDashboardComponent}, // canActivate: [AuthGuard], data: { role: 'PATIENT' } },
    { path: 'doctor-dashboard', component: DoctorDashboardComponent} //canActivate: [AuthGuard], data: { role: 'DOCTOR' } }

];
