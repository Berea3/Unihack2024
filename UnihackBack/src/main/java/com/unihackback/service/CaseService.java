package com.unihackback.service;

import com.unihackback.entity.Case;
import com.unihackback.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseService {

    CaseRepository caseRepository;

    @Autowired
    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public Case getCaseById(String caseId) {

        Optional<Case> caseOptional = caseRepository.findById(caseId);

        return caseOptional.orElse(null);

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

    public List<Case> getCasesByDoctor(String doctorId) {
        return caseRepository.findAllByDoctorId(doctorId);
    }

}