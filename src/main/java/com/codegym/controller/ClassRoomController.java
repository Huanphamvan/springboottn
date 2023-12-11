package com.codegym.controller;

import com.codegym.model.ClassRoom;
import com.codegym.model.Student;
import com.codegym.service.IClassRoomService;
import com.codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/classrooms")
@CrossOrigin("*")
public class ClassRoomController {

    private  IClassRoomService iClassRoomService;
    @Autowired

    private  IStudentService iStudentService;
    @Autowired


    public ClassRoomController(IClassRoomService iClassRoomService, IStudentService iStudentService) {
        this.iClassRoomService = iClassRoomService;
        this.iStudentService = iStudentService;
    }
    @GetMapping("")
    public ResponseEntity<Iterable<ClassRoom>> findAllClassRoom(){
        List<ClassRoom> classRoomList = (List<ClassRoom>) iClassRoomService.findAll();
        if (classRoomList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(classRoomList,HttpStatus.OK);
        }
    }
    @PostMapping("")
    public ResponseEntity<ClassRoom> add(@RequestBody ClassRoom classRoom){
        iClassRoomService.save(classRoom);
        return new ResponseEntity<>(classRoom,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ClassRoom> delete(@PathVariable Long id){
        Optional<ClassRoom> classRoomOptional = iClassRoomService.findById(id);
        if (!classRoomOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            iClassRoomService.remove(id);
            return new ResponseEntity<>(classRoomOptional.get(),HttpStatus.OK);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClassRoom> update(@PathVariable Long id,@RequestBody ClassRoom classRoom){
        Optional<ClassRoom> classRoomOptional = iClassRoomService.findById(id);
        if (!classRoomOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            classRoom.setId(classRoomOptional.get().getId());
            return new ResponseEntity<>(iClassRoomService.save(classRoom),HttpStatus.OK);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoom>findClassRoomById(@PathVariable Long id){
        return new ResponseEntity<>(iClassRoomService.findById(id).get(),HttpStatus.OK) ;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClassRoom>> getByName(@RequestParam String name) {
        System.out.println(name);
        List<ClassRoom> classRoomList = (List<ClassRoom>) iClassRoomService.findByNameContain(name);
        System.out.println(classRoomList);
        if (classRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classRoomList, HttpStatus.OK);
    }


}
