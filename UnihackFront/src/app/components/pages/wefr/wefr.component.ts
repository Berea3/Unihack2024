import { Component } from '@angular/core';
import {Case} from '../../../entities/case';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../../auth-service.service';
import {CaseServiceService} from '../../../services/caseService/case-service.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from '@angular/material/autocomplete';

@Component({
  selector: 'app-wefr',
  standalone: true,
  imports: [
      FormsModule,
      NgForOf,
      MatFormField,
      MatInput,
      ReactiveFormsModule,
      MatAutocompleteTrigger,
      MatAutocomplete,
      MatOption,
      AsyncPipe,
      NgIf,
      MatLabel
  ],
  templateUrl: './wefr.component.html',
  styleUrl: './wefr.component.css'
})
export class WefrComponent {

    constructor(private http: HttpClient, private router: Router, private authService: AuthService, private caseService: CaseServiceService) {}
    doctors: String[] =  []; // List of doctors

    ngOnInit() {

        this.http.get('http://localhost:1443/users/get-all-doctors').subscribe(
            (response: any) => {
                this.doctors = response;
                console.log("AAAAAAAAAAAAAAAAAAA");
                console.log(this.doctors);
            }
        );

    }
}
