package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.entity.Student;
import com.app.repository.StudentRepository;

@RestController
public class StudentRestController {

	@Autowired
	StudentRepository studentRepository;

	@GetMapping(value = "/student/list")
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/student/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isPresent()) {
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		}
		return null;
	}

	@DeleteMapping(value = "/student/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") Integer id) {
		studentRepository.deleteById(id);
		return new ResponseEntity<>("Student deleted by Id : " + id, HttpStatus.OK);
	}

	@PostMapping("/student")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student stud = studentRepository.saveAndFlush(student);
		return new ResponseEntity<>(stud, HttpStatus.CREATED);
	}

	@PutMapping("/student")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		Student stud = studentRepository.saveAndFlush(student);
		return new ResponseEntity<>(stud, HttpStatus.OK);
	}

}
