import { Routes } from '@angular/router';
import {AuthenticationComponent} from './components/authentication/authentication.component';
import {ClientDashboardComponent} from './components/pages/client-dashboard/client-dashboard.component';

export const routes: Routes = [
    { path: '', component: AuthenticationComponent },
    { path: 'client-dashboard', component: ClientDashboardComponent }
];
