import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Case } from '../../../entities/case';
import { User } from '../../../entities/user';
import { FormControl, FormsModule, NgForm } from '@angular/forms';
import { AuthService } from '../../auth-service.service';
import { MatFormField } from '@angular/material/form-field';
import { MatOption, MatSelect } from '@angular/material/select';
import {DatePipe, NgClass, NgForOf, NgIf} from '@angular/common';
import { MatList, MatListItem } from '@angular/material/list';
import { MatButton } from '@angular/material/button';
import {Appointment} from '../../../entities/appointment';
import {MAT_SNACK_BAR_DATA, MatSnackBar, matSnackBarAnimations} from '@angular/material/snack-bar';

@Component({
    selector: 'app-doctor-dashboard',
    templateUrl: './doctor-dashboard.component.html',
    standalone: true,
    imports: [
        MatFormField,
        MatSelect,
        DatePipe,
        MatOption,
        MatList,
        MatListItem,
        NgIf,
        NgForOf,
        FormsModule,
        MatButton,
        NgClass
    ],
    styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {

    doctorId: String | null = null;
    doctor: User;
    cases: Case[] = [];
    selectedCase: Case;
    isModalOpen = false;

    newAppointment: Appointment = new Appointment()
    isSummariseModalOpen: Boolean = false;
    newSummary: string = '';

    _snackBar: MatSnackBar;
    newPrescription: string = '';
    isEmailModalOpen: boolean = false;

    constructor(private http: HttpClient, private route: ActivatedRoute, private authService: AuthService, private router: Router) { }

    ngOnInit() {
        // Ensure doctorId is defined and fetched
        this.doctor = this.authService.getUser();

        console.log('User:', this.doctor);

        if (this.doctor && this.doctor.id) {
            this.doctorId = this.doctor.id;

            // Fetch cases using doctorId as a path variable in the URL
            this.http.get<Case[]>(`http://localhost:1443/case/cases-by-doctor/${this.doctorId}`).subscribe(
                (response) => {
                    console.log('Cases:', response);
                    this.cases = response;
                },
                (error) => {
                    console.error('Error fetching cases:', error);
                }
            );
        } else {
            // Handle case where doctorId is not available (e.g., redirect to login page)
            this.router.navigate(['/login']);
        }
    }

    openSnackBar(message: string) {
        this._snackBar.open(message, 'close', {
            duration: 2000,
        });
    }

    onCaseSelect(selectedCase: Case): void {

        this.newSummary = '';
        this.newPrescription = '';

        this.selectedCase = selectedCase;
    }

    requestAppointment() {

        console.log('Creating appointment:', this.newAppointment);

        this.http.post('http://localhost:1443/appointment/create/' + this.selectedCase?.id + '/' + this.doctor.email , this.newAppointment).subscribe(
            (response) => {
                console.log('Appointment created:');
                this.isModalOpen = false;
            },
            (error) => {
                console.error('Error creating appointment:', error);
            }
        );
    }

    openModal() {
        this.isModalOpen = true;
    }

    closeModal() {
        this.isModalOpen = false;
    }

    updateCase()
    {
        this.selectedCase.caseDescription = this.newSummary;
        this.http.put("http://localhost:1443/case/update", this.selectedCase).subscribe();
    }

    openSummariseModal() {
        this.isSummariseModalOpen = true;
    }

    summariseCase() {

        console.log(this.selectedCase.id)

        this.http.get(`http://localhost:1443/ai/summarize-case/` + this.selectedCase.id,{responseType:'text'}).subscribe(
            (response) => {
                this.newSummary = response;
                console.log('Case summarised:', response);
                this.newSummary=response;
                // this.isSummariseModalOpen = false;
            },
            (error) => {
                console.error('Error summarising case:', error);
            }
        );
    }

    closeSummariseModal() {
        this.isSummariseModalOpen = false;
    }

    sendToUserEmail() {

        let finalString = 'Summary:\n';

        if(this.newSummary == '')
        {
            finalString = 'No summary for this case\n';
        }
        else
        {
            finalString += this.newSummary;
        }

        finalString += '%%Case Result: ';

        if(this.selectedCase.caseResult == '')
        {
            finalString += 'No result for this case';
        }
        else
        {
            finalString += this.selectedCase.caseResult;
        }

        finalString += '%%';

        if(this.newPrescription != '')
        {
            finalString += this.newPrescription;
        }
        else
        {
            finalString += 'No prescription for this case';
        }

        finalString += '%%';

        console.log(finalString)

        this.http.post(`http://localhost:1443/email/send/${this.selectedCase.id}`, finalString).subscribe(
            (response) => {
            },
            () => {
            }
        );

        this.closeSummariseModal()
        this.closeEmailModal()
    }

    createPrescription() {
        this.http.post(`http://localhost:1443/ai/write-prescription`, this.newSummary, {responseType:'text'}).subscribe(
            (response) => {
                this.newPrescription = response;
            },
            (error) => {
                console.error('Error creating prescription:', error);
            }
        );
    }

    openEmailModal() {
        this.isEmailModalOpen = true;
    }

    closeEmailModal() {
        this.isEmailModalOpen = false;
    }
}
