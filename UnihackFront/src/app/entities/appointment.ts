import { Case } from './case';

export class Appointment {
    id: string;                // Primary key as string
    appointmentName: string;   // Appointment name
    appointmentDescription: string;  // Appointment description
    parentCase: Case;          // Parent Case to which the appointment belongs
}
