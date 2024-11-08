package com.unihackback.repository;

import com.unihackback.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, String> {

    @Query("SELECT c FROM Case c WHERE c.doctor_id = ?1")
    Case findByDoctorId(String doctorId);
}
