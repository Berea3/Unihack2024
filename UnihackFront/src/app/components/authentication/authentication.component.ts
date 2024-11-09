import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth-service.service';
import { Router } from '@angular/router';
import { User } from '../../entities/user';
import { Application } from '@splinetool/runtime';

@Component({
    selector: 'app-authentication',
    standalone: true,
    imports: [
        NgIf,
        FormsModule
    ],
    templateUrl: './authentication.component.html',
    styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {
    @ViewChild('canvas3d') canvas3dRef: ElementRef | undefined;
    isLoginForm = true;
    user: User = { email: '', password: '', id: '', roles: '', cases: undefined };

    constructor(private authService: AuthService, private router: Router) {}

    ngOnInit() {
        setTimeout(() => {
            if (this.canvas3dRef) {
                const app = new Application(this.canvas3dRef.nativeElement);
                app.load('https://prod.spline.design/5UlqI6DUbTEwl9Rp/scene.splinecode').then(r => {
                    console.log('Spline app loaded');
                });
            }
        }, 0);
    }

    toggleForm() {
        this.isLoginForm = !this.isLoginForm;
        this.user = { email: '', password: '', id: '', roles: '', cases: undefined };
    }

    login() {
        this.authService.login(this.user).subscribe(response => {
            if (response) {
                this.router.navigate(['dashboard']).then(() => console.log('Navigated to dashboard'));
            } else {
                alert("Wrong credentials");
            }
        });
    }

    signup() {
        this.authService.signUp(this.user).subscribe(response => {
            if (response) {
                alert("User signed up successfully");
                this.router.navigate(['dashboard']).then(() => console.log('Navigated to dashboard'));
            }
        });
    }
}
