import { Report } from './report';
import { User } from './user';

import { Prescription } from './prescription';
import {Appointment} from './appointment';

export class Case {
    id: string;                // Primary key as string
    caseName: string;          // Case name
    caseDescription: string;   // Case description
    caseStatus: string;        // Case status
    caseCategory: string;      // Case category
    caseDate: string;          // Case date (as string in ISO format, equivalent to LocalDate)
    caseResult: string;        // Case result

    reports: Report[] = [];    // List of reports associated with the case
    users: User[] = [];        // List of users associated with the case
    appointments: Appointment[] = [];  // List of appointments associated with the case
    prescriptions: Prescription[] = []; // List of prescriptions associated with the case
}
