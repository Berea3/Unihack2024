import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Route, Router} from '@angular/router';
import {User} from '../../entities/user';
import { Application } from '@splinetool/runtime';

@Component({
    selector: 'app-authentication',
    standalone: true,
    imports: [
        NgIf,
        FormsModule
    ],
    templateUrl: './authentication.component.html',
    styleUrl: './authentication.component.css'
})
export class AuthenticationComponent implements  OnInit{

    @ViewChild('canvas3d') canvas3dRef: ElementRef | undefined;
    isLoginForm = true;

    user: User = { email: '', password: '', id: '', roles: '', cases: undefined }; // Initialize user object

    canvas = document.getElementById('canvas3d');

    constructor(private http: HttpClient, private router: Router) {}

    ngOnInit() {
        // Delay loading of the Spline app to ensure view is initialized
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
    }

    login(){
        const formData : FormData=new FormData();
        this.http.post('http://localhost:1443/login', this.user).subscribe(
            (response: any)=>{
                console.log()
                this.user=response;
                if (this.user.id==null)
                {
                    alert("wrong credentials");
                }
                else
                {
                    sessionStorage.setItem("id",this.user.id.toString());
                    sessionStorage.setItem("roles",this.user.roles.toString());

                    this.router.navigate(['client-dashboard']).then(r => console.log('Navigated to client dashboard'));

                }
            }
        );
    }

    signup(){

        this.http.post('http://localhost:1443/security/sign-up', this.user).subscribe(
            (response: any)=>{

                sessionStorage.setItem("id",this.user.id.toString());
                sessionStorage.setItem("roles",this.user.roles.toString());
            }
        );
    }
}
