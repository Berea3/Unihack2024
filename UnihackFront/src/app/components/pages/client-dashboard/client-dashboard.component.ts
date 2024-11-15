import {Component, inject, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../../auth-service.service';
import {CaseServiceService} from '../../../services/caseService/case-service.service';
import {Case} from '../../../entities/case';
import {User} from '../../../entities/user';
import {FormControl, FormsModule, NgForm, ReactiveFormsModule} from '@angular/forms';
import {AsyncPipe, DatePipe, NgClass, NgForOf, NgIf} from '@angular/common';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from '@angular/material/autocomplete';
import {delay, Observable} from 'rxjs';
import {MatSelect} from '@angular/material/select';
import {Report} from '../../../entities/report';
import {DoctorDashboardComponent} from '../doctor-dashboard/doctor-dashboard.component';
import {Appointment} from '../../../entities/appointment';
import {MatSnackBar} from '@angular/material/snack-bar';

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
        NgIf,
        MatLabel,
        MatSelect,
        NgClass,
        DatePipe
    ],
    templateUrl: './client-dashboard.component.html',
    styleUrls: ['./client-dashboard.component.css']
})
export class ClientDashboardComponent implements OnInit {

    latestReport: Report = new Report();

    appointments: Appointment[] = [];

    constructor(private http: HttpClient, private router: Router, private authService: AuthService, private caseService: CaseServiceService) {}

    doctors: User[]; // List of doctors
    user: User;
    cases: Case[] = [];

    report: Report = new Report();

    doctor: User = new User();

    caseId : String = '';

    appointmentModalOpen = false;

    newAppointment: Appointment = new Appointment();

    operationResult: String = '';

    private _snackBar = inject(MatSnackBar);

    ngOnInit() {
        this.user = this.authService.getUser();

        this.http.get('http://localhost:1443/appointment/find-all-by-user-id/' + this.user.id).subscribe((response:any) => {
            console.log('Appointments:', response);
            this.appointments = response;

        });

        this.http.get('http://localhost:1443/users/get-all-doctors').subscribe((response : any) => {
            console.log(response);

            this.doctors = response;
        })

        // Fetch cases list
        this.http.get<Case[]>('http://localhost:1443/case/cases-by-doctor/' + this.user.id).subscribe(response => {
            this.cases = response;
        });

        this.http.get<Report>('http://localhost:1443/report/latest-report-by-user/' + this.user.id).subscribe(response => {
            console.log('Latest report:', response);
            this.latestReport = response;
        });
    }

    openSnackBar(message: string) {
        this._snackBar.open(message, 'close');
    }

    addCase(caseForm: NgForm) {

        for(let i = 0; i < this.doctors.length; i++) {
            console.log('Doctor:', this.doctors[i]);
            if(this.doctors[i].email === caseForm.value.caseDoctor) {
                this.doctor = this.doctors[i];
                break;
            }
        }

        console.log('Doctor:', this.doctor);

        const newCase: Case = {
            id: '',
            caseName: caseForm.value.caseName,
            caseDescription: caseForm.value.caseDescription,
            caseStatus: 'NEW',
            caseCategory: caseForm.value.caseCategory,
            caseDate: caseForm.value.caseDate,
            caseResult: caseForm.value.caseResult,
            reports: [],
            users: [this.doctor, this.user], // Link the selected doctor to this case
            appointments: [],
            prescriptions: []
        };

        this.closeModal()

        this.http.post('http://localhost:1443/case/add-case', newCase).subscribe((response: any) => {
        });

        this.openSnackBar('Case added successfully');
    }

    // Modal open state
    isModalOpen = false;

    // Open the modal
    openModal(caseId:string) {
        this.caseId = caseId

        this.isModalOpen = true;
    }

    openAppointmentModal() {
        this.appointmentModalOpen = true;
    }

    // Close the modal
    closeModal() {
        this.isModalOpen = false;

    }

    addReport() {

        this.http.post('http://localhost:1443/report/create/' + this.caseId, this.report).subscribe((response: any) => {
        });

        delay(200);

        this.http.get<Case[]>('http://localhost:1443/case/cases-by-doctor/' + this.user.id).subscribe(response => {
            this.cases = response;
        })

        this.openSnackBar('Report added successfully');

        // Reset the report object to prevent old data from affecting the next addition
        this.report = new Report();

        this.closeModal()
    }

    requestAppointment(appointmentCase: Case) {
        console.log('Creating appointment:', this.newAppointment);

        this.http.post('http://localhost:1443/appointment/create/' + appointmentCase.id + '/' + this.user.email , this.newAppointment).subscribe(
            (response) => {
                console.log('Appointment created:');
                this.isModalOpen = false;
            },
            (error) => {
                console.error('Error creating appointment:', error);
            }
        );

        this.openSnackBar('Appointment requested successfully');
    }

    closeAppointmentModal() {
        this.appointmentModalOpen = false;
    }
}
