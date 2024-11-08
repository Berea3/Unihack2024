package com.unihackback.controller;

import com.unihackback.entity.Case;
import com.unihackback.service.CaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/case")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping(value = "/case/{caseId}", produces = "application/json")
    public Case getCaseById(@PathVariable String caseId) {
        return caseService.getCaseById(caseId);
    }

    @GetMapping(value = "/cases-by-doctor/{doctorId}", produces = "application/json")
    public List<Case> getCasesByDoctor(@PathVariable String doctorId) {
        return caseService.getCasesByDoctor(doctorId);
    }
}
