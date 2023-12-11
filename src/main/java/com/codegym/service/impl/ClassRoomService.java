package com.codegym.service.impl;

import com.codegym.model.ClassRoom;
import com.codegym.repository.IClassRoomRepository;
import com.codegym.service.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService implements IClassRoomService {

    private IClassRoomRepository iClassRoomRepository;
    @Autowired
    public ClassRoomService (IClassRoomRepository iClassRoomRepository){
        this.iClassRoomRepository=iClassRoomRepository;
    }


    @Override
    public Iterable<ClassRoom> findAll() {
        return iClassRoomRepository.findAll();
    }

    @Override
    public Optional<ClassRoom> findById(Long id) {
        return iClassRoomRepository.findById(id);
    }

    @Override
    public ClassRoom save(ClassRoom classRoom) {
        return iClassRoomRepository.save(classRoom);
    }

    @Override
    public void remove(Long id) {
        iClassRoomRepository.deleteById(id);

    }

    @Override
    public List<ClassRoom> findByNameContain(String name) {
        return iClassRoomRepository.findByNameContainsIgnoreCase(name);
    }
}
