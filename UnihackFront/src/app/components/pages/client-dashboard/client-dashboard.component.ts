import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../../auth-service.service';
import {CaseServiceService} from '../../../services/caseService/case-service.service';
import {Case} from '../../../entities/case';
import {User} from '../../../entities/user';
import {FormsModule, NgForm} from '@angular/forms';

@Component({
    selector: 'app-client-dashboard',
    standalone: true,
    imports: [
        FormsModule
    ],
    templateUrl: './client-dashboard.component.html',
    styleUrl: './client-dashboard.component.css'
})
export class ClientDashboardComponent implements OnInit {



    constructor(private http: HttpClient, private router: Router, private authService: AuthService, private caseService: CaseServiceService) {
    }

    doctors: User[] = [];
    user: User | null = null;
    cases: Case[];

    ngOnInit() {
        const user = this.authService.getUser();

        this.user = this.authService.getUser();

        console.log('User:', user);

        if (user?.id) {
            this.http.get(`http://localhost:1443/case/cases-by-doctor/${user.id}`).subscribe((response: any) => {
                    this.cases = response;
                    console.log('Cases:', this.cases);
                }
            );

            this.http.get('http://localhost:1443/users/get-all-doctors').subscribe((response: any) => {
                this.doctors = response;
                console.log('Doctors:', this.doctors);
            });
        }
    }

    logout() {
        this.authService.logout();
    }

    addCase(caseForm: NgForm) {
        const newCase: Case = {
            id: '',  // You can handle the ID in the backend if it's auto-generated
            caseName: caseForm.value.caseName,
            caseDescription: caseForm.value.caseDescription,
            caseStatus: 'NEW',
            caseCategory: caseForm.value.caseCategory,
            caseDate: caseForm.value.caseDate,
            caseResult: caseForm.value.caseResult,
            reports: [],  // Initialize reports list (this can be populated later)
            users: [],  // Initialize users list (this can be populated later)
            appointments: [],  // Initialize appointments list (this can be populated later)
            prescriptions: []  // Initialize prescriptions list (this can be populated later)
        };

        this.http.post('http://localhost:1443/case/add-case', newCase).subscribe((response: any) => {
            console.log('Case added:', response);
            this.cases.push(response);
        });
    }
}
