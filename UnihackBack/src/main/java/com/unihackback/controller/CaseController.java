package com.unihackback.controller;

import com.unihackback.entity.Case;
import com.unihackback.entity.Report;
import com.unihackback.entity.generator.Generator;
import com.unihackback.repository.CaseRepository;
import com.unihackback.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/case")
public class CaseController {

    private final CaseService caseService;

    @Autowired
    CaseRepository caseRepository;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping(value = "/case/{caseId}", produces = "application/json")
    public Case getCaseById(@PathVariable String caseId) {
        return caseService.getCaseById(caseId);
    }

    @GetMapping(value = "/cases-by-doctor/{userId}")
    public List<Case> getCasesByUserId(@PathVariable String userId) {

        List<Case> cases = caseService.getCasesByDoctor(userId);

        for (int i = 0; i < cases.size(); i++) {
            List<Report> reports= cases.get(i).getReports();
        }

        return caseService.getCasesByDoctor(userId);
    }

    @PostMapping( value = "/add-case", consumes = "application/json")
    public void saveCase(@RequestBody Case caseObj) {
        caseObj.setId(Generator.generateId());
        caseService.saveCase(caseObj);
    }

    @PutMapping("/updateStatus/{id}")
    public void update(@RequestBody String status, @PathVariable String id)
    {
        Case pacientCase=caseRepository.findById(id).get();
        pacientCase.setCaseStatus(status);
        caseRepository.save(pacientCase);
    }

    @PutMapping("/update")
    public void update(@RequestBody Case pacientCase)
    {
        caseRepository.save(pacientCase);
    }
}
