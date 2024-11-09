import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from '../../auth-service.service';
import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(private authService: AuthService, private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        const expectedRole = route.data['role'];
        const user = this.authService.getUser();

        if (!user || (expectedRole && user.roles !== expectedRole)) {
            this.router.navigate(['login']);
            console.log('User not logged in or unauthorized');
            return false;
        }

        return true;
    }
}
