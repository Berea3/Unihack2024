import {Component} from '@angular/core';
import {NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import * as Http from 'node:http';
import {HttpClient} from '@angular/common/http';
import {Router} from 'express';
import {User} from '../../entities/user';

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
export class AuthenticationComponent {

    isLoginForm = true;

    email: string;
    username: string;
    password: string;
    user: User;

    constructor(private http: HttpClient, private router: Router) {}

    toggleForm() {
        this.isLoginForm = !this.isLoginForm;
    }

    login(){
        const formData : FormData=new FormData();
        formData.append('username',this.email);
        formData.append('password',this.password);
        formData.append('rememberMe',"true");
        this.http.post('http://localhost:1443/login',formData).subscribe(
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
                }
            }
        );
    }

}
