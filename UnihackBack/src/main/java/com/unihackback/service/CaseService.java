package com.unihackback.service;

import com.unihackback.entity.Case;
import com.unihackback.repository.CaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CaseService {

    CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public void getCaseById(String caseId) {
        caseRepository.findById(caseId);
    }

    public void getAllCases() {
        caseRepository.findAll();
    }

    public void saveCase(Case caseToSave) {
        caseRepository.save(caseToSave);
    }

    public void deleteCase(String caseId) {
        caseRepository.deleteById(caseId);
    }

    public String updateCase(String caseId, Case caseToUpdate) {
        if (caseRepository.findById(caseId).isEmpty()) {
            return "Case not found";
        }

        caseRepository.save(caseToUpdate);

        return "Case updated";
    }
}