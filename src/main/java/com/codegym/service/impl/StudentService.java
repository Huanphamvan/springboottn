package com.codegym.service.impl;

import com.codegym.model.Student;
import com.codegym.model.Tutor;
import com.codegym.repository.IStudentRepository;
import com.codegym.repository.ITutorRepository;
import com.codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class StudentService implements IStudentService {

    private  IStudentRepository iStudentRepository;
    @Autowired
    public StudentService(IStudentRepository iStudentRepository) {
        this.iStudentRepository = iStudentRepository;
    }
    @Autowired
    private ITutorRepository iTutorRepository;

    @Override
    public Iterable<Student> findAll() {
        return iStudentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return iStudentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        Set<Tutor> tutors = student.getTutors();
        if (tutors!= null && !tutors.isEmpty()){
            Set<Tutor> managedTutor = new HashSet<>();
            for (Tutor element: tutors) {
                if (element.getId() != null){
                    Optional<Tutor> optionalTutor = iTutorRepository.findById(element.getId());
                    optionalTutor.ifPresent(managedTutor::add);
                }

            }
            student.setTutors(managedTutor);

        }

        return iStudentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
        iStudentRepository.deleteById(id);

    }

    @Override
    public List<Student> findByNameContain(String name) {
        return iStudentRepository.findByNameContainsIgnoreCase(name);
    }
}

