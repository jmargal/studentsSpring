package com.jacaranda.studentsDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.studentsDB.model.Student;

public interface StudentRepositorio extends JpaRepository<Student,Long> {

}
