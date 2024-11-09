package com.unihackback.controller;

import com.unihackback.entity.Case;
import com.unihackback.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/case")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping(value = "/case/{caseId}", produces = "application/json")
    public Case getCaseById(@PathVariable String caseId) {
        return caseService.getCaseById(caseId);
    }

    @GetMapping(value = "/cases-by-doctor/{userId}", produces = "application/json")
    public List<Case> getCasesByUserId(@PathVariable String userId) {
        return caseService.getCasesByDoctor(userId);
    }

    @PostMapping(value = "/case", consumes = "application/json")
    public ResponseEntity<String> saveCase(@RequestBody Case caseObj) {
        caseService.saveCase(caseObj);
        return ResponseEntity.ok("Case saved successfully");
    }
}
