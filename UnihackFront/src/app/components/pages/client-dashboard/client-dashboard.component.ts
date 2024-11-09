import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../../auth-service.service';
import {CaseServiceService} from '../../../services/caseService/case-service.service';
import {Case} from '../../../entities/case';
import {User} from '../../../entities/user';
import {FormControl, FormsModule, NgForm, ReactiveFormsModule} from '@angular/forms';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {MatFormField} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from '@angular/material/autocomplete';
import {Observable} from 'rxjs';

@Component({
    selector: 'app-client-dashboard',
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
        NgIf
    ],
    templateUrl: './client-dashboard.component.html',
    styleUrls: ['./client-dashboard.component.css']
})
export class ClientDashboardComponent implements OnInit {

    constructor(private http: HttpClient, private router: Router, private authService: AuthService, private caseService: CaseServiceService) {}

    doctorControl = new FormControl<User | string>('');
    doctors: User[] =  []; // List of doctors
    user: User | null = null;
    cases: Case[] = [];
    selectedDoctor: User;

    ngOnInit() {
        this.http.get<User[]>('http://localhost:1443/user/doctors').subscribe(response => {
            this.doctors = response;
        })

        console.log('Doctors:', this.doctors);

        // Fetch cases list
        this.http.get<Case[]>('http://localhost:1443/case/cases-by-doctor/' + this.user?.id).subscribe(response => {
            console.log('Cases:', response);
            this.cases = response;
        });
    }

    addCase(caseForm: NgForm) {
        const newCase: Case = {
            id: '',
            caseName: caseForm.value.caseName,
            caseDescription: caseForm.value.caseDescription,
            caseStatus: 'NEW',
            caseCategory: caseForm.value.caseCategory,
            caseDate: caseForm.value.caseDate,
            caseResult: caseForm.value.caseResult,
            reports: [],
            users: [this.selectedDoctor], // Link the selected doctor to this case
            appointments: [],
            prescriptions: []
        };

        this.http.post('http://localhost:1443/case/add-case', newCase).subscribe((response: any) => {
            console.log('Case added:', newCase);
            this.cases.push(newCase);
        });
    }
}
