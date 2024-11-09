import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../../auth-service.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(private authService: AuthService, private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        const expectedRole = route.data['role'];
        const user = this.authService.getUser();

        if (!this.authService.isLoggedIn() || (expectedRole && user?.roles !== expectedRole)) {
            this.router.navigate(['login']).then(r => {
                console.log('User not logged in or unauthorized')
                console.log(user);
            });
            return false;
        }

        return true;
    }
}
