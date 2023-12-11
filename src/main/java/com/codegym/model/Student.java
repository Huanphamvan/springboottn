package com.codegym.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;

    @ManyToOne
    private ClassRoom classRoom;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "student_tutor",
            joinColumns = { @JoinColumn(name = "student_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tutor_id",referencedColumnName = "id")}
    )
    private Set<Tutor> tutors;


}
