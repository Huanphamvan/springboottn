package com.codegym.controller;

import com.codegym.model.Student;
import com.codegym.service.IClassRoomService;
import com.codegym.service.IStudentService;
import com.codegym.service.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentController {

    private final ITutorService iTutorService;
    @Autowired
    private final IStudentService iStudentService;
    @Autowired
    private final IClassRoomService iClassRoomService;
    @Autowired
    public StudentController(IStudentService iStudentService, IClassRoomService iClassRoomService, ITutorService iTutorService1) {
        this.iStudentService = iStudentService;
        this.iClassRoomService = iClassRoomService;
        this.iTutorService = iTutorService1;
    }



    @GetMapping("")
    public ResponseEntity<Iterable<Student>> findAllStudent(){
        List<Student> studentList= (List<Student>) iStudentService.findAll();
        if (studentList.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Student>add( @RequestBody Student student){
        iStudentService.save(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id){
        Optional<Student> studentOptional = iStudentService.findById(id);
        if (!studentOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            iStudentService.remove(id);
            return new ResponseEntity<>(studentOptional.get(),HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateCustomer(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> studentOptional = iStudentService.findById(id);
        if (!studentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        student.setId(studentOptional.get().getId());
        return new ResponseEntity<>(iStudentService.save(student), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student>findStudentById(@PathVariable Long id){
       return new ResponseEntity<>(iStudentService.findById(id).get(),HttpStatus.OK) ;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> getByName(@RequestParam String name) {
        System.out.println(name);
        List<Student> students = (List<Student>) iStudentService.findByNameContain(name);
        System.out.println(students);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }



}