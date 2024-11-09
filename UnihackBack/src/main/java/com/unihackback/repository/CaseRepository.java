package com.unihackback.repository;

import com.unihackback.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<Case, String> {

    @Query("SELECT c FROM Case c JOIN c.users u WHERE u.id = :doctorId")
    List<Case> findAllByDoctorId(String doctorId);

    @Query("SELECT distinct c FROM Case c JOIN c.users u WHERE u.id = :id")
    List<Case> findByPatientId(String id);

//    @Query("SELECT c FROM c c WHERE c. = ?1")
//    Case findByDoctorId(String doctorId);

}
