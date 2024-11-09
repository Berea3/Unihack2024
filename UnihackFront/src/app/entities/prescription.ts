import { Case } from './case';

export class Prescription {
    id: string;                    // Primary key as string
    prescriptionName: string;      // Prescription name
    prescriptionDescription: string; // Prescription description
    prescriptionResult: string;    // Prescription result

    parentCase: Case;              // Parent Case to which the prescription belongs
}
