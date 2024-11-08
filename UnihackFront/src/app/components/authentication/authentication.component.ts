import {Component} from '@angular/core';
import {NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
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

    user: User = { email: '', password: '', id: '', roles: '', cases: undefined }; // Initialize user object

    constructor(private http: HttpClient) {}

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
                }
            }
        );
    }

    signup(){

        this.http.post('http://localhost:1443/security/sign-up', this.user).subscribe(
            (response: any)=>{
                console.log(response);
                this.user=response;
                console.log(this.user);

                sessionStorage.setItem("id",this.user.id.toString());
                sessionStorage.setItem("roles",this.user.roles.toString());
            }
        );
    }
}
