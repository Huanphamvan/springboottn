package com.codegym.repository;

import com.codegym.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ITutorRepository extends JpaRepository<Tutor,Long> {
}
