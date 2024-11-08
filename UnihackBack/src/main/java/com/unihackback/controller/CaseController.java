package com.unihackback.controller;

import com.unihackback.entity.Case;
import com.unihackback.service.CaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/case")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping(value = "/case/{caseId}", produces = "application/json")
    public Case getCaseById(String caseId) {
        return caseService.getCaseById(caseId);
    }

    @GetMapping(value = "/cases-by-doctor/{doctorId}", produces = "application/json")
    public Case getCasesByDoctor(String doctorId) {
        return caseService.getCasesByDoctor(doctorId);
    }


}
