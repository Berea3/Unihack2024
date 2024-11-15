import { Case } from './case';

export class Report {
    reportId: string;           // Primary key as a string
    reportName: string;         // Name of the report
    reportDescription: string;  // Description of the report
    reportPriority: string;     // Priority level, e.g., 'Low', 'Medium', 'High'
    reportDate: string;         // Report date as a string in ISO format (e.g., '2023-11-09')
    parentCase: Case;          // Optional parent case; can be null or undefined if omitted
}

