package com.codegym.service;

import com.codegym.model.Student;

import java.util.List;

public interface IStudentService extends IGeneralService<Student>{

    List<Student> findByNameContain(String name);
}
