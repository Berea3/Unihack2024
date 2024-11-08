import {Report} from './report';

export class Case{

    id: String;
    caseName: String;
    caseDescription: String;
    caseStatus: String;
    caseCategory: String;
    caseDate: String;
    caseResult: String;


    reports: Report[]=[];
}
