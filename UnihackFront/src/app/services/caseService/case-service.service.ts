import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CaseServiceService {

    constructor(private http: HttpClient) { }

    getCasesByUserId(id: String) {

        return this.http.get(`http://localhost:1443/case/cases-by-doctor/${id}`);

    }
}
