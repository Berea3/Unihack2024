package com.unihackback.repository;

import com.unihackback.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, String> {

}
