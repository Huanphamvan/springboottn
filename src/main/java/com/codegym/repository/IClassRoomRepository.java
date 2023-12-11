package com.codegym.repository;

import com.codegym.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClassRoomRepository extends JpaRepository<ClassRoom,Long> {
    List<ClassRoom> findByNameContainsIgnoreCase(String name);
}
