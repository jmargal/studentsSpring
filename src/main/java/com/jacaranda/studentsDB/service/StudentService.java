package com.jacaranda.studentsDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.studentsDB.model.Student;
import com.jacaranda.studentsDB.repository.StudentRepositorio;

@Service
public class StudentService {

	@Autowired
	StudentRepositorio repositorio;

	public Student getStudent(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public List<Student> getStudents() {
		return repositorio.findAll();
	}

	public Student add(Student s) {
		return repositorio.save(s);
	}

	public void delete(Student s) {
		repositorio.delete(s);
	}

	public Student update(Student s) {
		if (getStudent(s.getId()) != null) {
			return repositorio.save(s);

		} else {
			return null;
		}
	}
}
