package com.codegym.controller;

import com.codegym.model.Student;
import com.codegym.model.Tutor;
import com.codegym.service.IClassRoomService;
import com.codegym.service.IStudentService;
import com.codegym.service.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tutors")
@CrossOrigin("*")
public class TutorController {
    private final ITutorService iTutorService;
    @Autowired
    private final IStudentService iStudentService;
    @Autowired
    private final IClassRoomService iClassRoomService;
    @Autowired

    public TutorController(ITutorService iTutorService, IStudentService iStudentService, IClassRoomService iClassRoomService) {
        this.iTutorService = iTutorService;
        this.iStudentService = iStudentService;
        this.iClassRoomService = iClassRoomService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Tutor>> findAllTutor(){
        List<Tutor> tutorList= (List<Tutor>) iTutorService.findAll();
        if (tutorList.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tutorList,HttpStatus.OK);
    }


}
