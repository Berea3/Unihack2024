package com.unihackback.repository;

import com.unihackback.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    @Query("SELECT a FROM Appointment a JOIN a.parentCase c JOIN c.users u WHERE u.id = :userId")
    List<Appointment> findAllByUserId(String userId);
}
