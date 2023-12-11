package com.codegym.service.impl;

import com.codegym.model.Tutor;
import com.codegym.repository.ITutorRepository;
import com.codegym.service.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TutorService implements ITutorService {
    @Autowired
    private ITutorRepository iTutorRepository;

    @Override
    public Iterable<Tutor> findAll() {
        return iTutorRepository.findAll();
    }

    @Override
    public Optional<Tutor> findById(Long id) {
        return iTutorRepository.findById(id);
    }

    @Override
    public Tutor save(Tutor tutor) {
        return iTutorRepository.save(tutor);
    }

    @Override
    public void remove(Long id) {
        iTutorRepository.deleteById(id);
    }
}
