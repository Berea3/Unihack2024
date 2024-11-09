import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../entities/user';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private currentUser: User | null = null;

    constructor(private http: HttpClient, private router: Router) {
        this.loadUserFromStorage();
    }

    login(user: User): Observable<User> {
        const formData: FormData = new FormData();
        formData.append('username', user.email as string);
        formData.append('password', user.password as string);

        return this.http.post<User>('http://localhost:1443/login', formData).pipe(
            tap((response: User) => {
                if (response.id) {
                    this.currentUser = response;
                    localStorage.setItem('user', JSON.stringify(response)); // Store user in localStorage
                } else {
                    alert("Invalid credentials");
                }
            })
        );
    }

    logout() {
        this.currentUser = null;
        localStorage.removeItem('user');
        this.router.navigate(['login']);
    }

    isLoggedIn(): boolean {
        return !!this.getUser();
    }

    getUser(): User | null {

        let isLoggedOnBackend = 'no';

        // this.http.get('http://localhost:1443/security').subscribe((response: any) => {
        //     console.log('isLoggedOnBackend', response);
        //     isLoggedOnBackend = response;
        // });

        // if (isLoggedOnBackend === 'yes') {
        //     return this/**/.currentUser;
        // }

        // localStorage.clear();
        return this.currentUser;
    }

    hasRole(role: string): boolean {
        return this.getUser()?.roles === role;
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

    private loadUserFromStorage() {
        const storedUser = localStorage.getItem('user');
        if (storedUser) {
            this.currentUser = JSON.parse(storedUser);
        }
    }

    signUp(user: User) {

        return this.http.post<User>('http://localhost:1443/security/sign-up', user).pipe(
            tap((response: User) => {
                if (response.id) {
                    this.currentUser = response;
                    localStorage.setItem('user', JSON.stringify(response)); // Store user in localStorage
                } else {
                    alert("Invalid credentials");
                }
            })
        );

    }
}
