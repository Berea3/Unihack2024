package com.unihackback.controller;

import com.unihackback.email.Email;
import com.unihackback.entity.Appointment;
import com.unihackback.entity.Case;
import com.unihackback.entity.generator.Generator;
import com.unihackback.repository.AppointmentRepository;
import com.unihackback.repository.CaseRepository;
import com.unihackback.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    Email email;

    @PostMapping("/create/{caseId}/{userMail}")
    public void create(@RequestBody Appointment appointment, @PathVariable String caseId, @PathVariable String userMail)
    {
        Case pacientCase=caseRepository.findById(caseId).get();
        List<User> users=pacientCase.getUsers();
        for (int i=0;i<users.size();i++)
        {
            if (!Objects.equals(users.get(i).getEmail(), userMail))
            {
                email.send(users.get(i).getEmail(),"Appointment","Dear sir/madam\n You have an appointment");
                break;
            }
        }
        appointment.setId(Generator.generateId());
        Case patientCase=caseRepository.findById(caseId).get();
        patientCase.addAppointment(appointment);
        caseRepository.save(patientCase);
    }

    @GetMapping("/find-all-by-user-id/{id}")
    public List<Appointment> getAppointmentsByUserId(@PathVariable String id) {
        return appointmentRepository.findAllByUserId(id);
    }

}
