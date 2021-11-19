package com.missouristate.csc450.socer.example;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudents(){
        return List.of(
                new Student(
                        1L,
                        "Hamza",
                        "hamzak@gmail.com",
                        LocalDate.of(1999, Month.MAY, 9),
                        22
                )
        );
    }
}
