import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../entities/user';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private loginUrl = 'http://localhost:1443/login';
    private signUpUrl = 'http://localhost:1443/security/sign-up';

    currentUser: User | null = null;

    constructor(private http: HttpClient, private router: Router) {}

    // Log in the user
    login(user: User): Observable<any> {
        if(!user.email || !user.password) {
            console.error('Invalid login credentials');
            return of(null);
        }
        const formData: FormData = new FormData();
        formData.append('username', String(user.email));
        formData.append('password', String(user.password));

        return this.http.post(this.loginUrl, formData).pipe(
            tap((response: any) => {
                if (response?.id) {
                    // Save session information to session storage
                    sessionStorage.setItem('id', response.id.toString());
                    sessionStorage.setItem('roles', response.roles.toString());

                    this.currentUser = response;
                }
            }),
            catchError(error => {
                console.error('Login failed', error);
                return of(null); // Return null on error
            })
        );
    }

    // Sign up the user
    signUp(user: User): Observable<any> {
        return this.http.post(this.signUpUrl, user).pipe(
            tap((response: any) => {
                alert('User created successfully');
                sessionStorage.setItem('id', response.id.toString());
                sessionStorage.setItem('roles', response.roles.toString());
            }),
            catchError(error => {
                console.error('Signup failed', error);
                return of(null);
            })
        );
    }

    // Check if a user is logged in
    isLoggedIn(): boolean {
        return !!sessionStorage.getItem('id');
    }

    // Get the user ID from session
    getUserId(): string | null {
        return sessionStorage.getItem('id');
    }

    getUser(): User | null {
        return this.currentUser || JSON.parse(sessionStorage.getItem('user') || 'null');
    }

    // Get user roles from session
    getUserRoles(): string | null {
        return sessionStorage.getItem('roles');
    }

    // Log out the user
    logout(): void {
        sessionStorage.clear();
        this.router.navigate(['/login']).then(() => console.log('User logged out'));
    }

    private getRedirectPath(): string {
        switch (this.getUser()?.roles) {
            case 'DOCTOR':
                return 'doctor-dashboard';
            case 'ADMIN':
                return 'admin-dashboard';
            default:
                return 'dashboard';
        }
    }
}
