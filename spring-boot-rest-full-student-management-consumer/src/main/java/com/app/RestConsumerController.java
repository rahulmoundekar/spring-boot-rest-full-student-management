package com.app;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.entity.Student;

@RestController
public class RestConsumerController {

	private RestTemplate restTemplate;

	private static final String STUDENT_URL = "http://localhost:8085/student";

	public RestConsumerController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping(value = "list")
	public Student[] getStudentList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(STUDENT_URL + "/list", HttpMethod.GET, entity, Student[].class).getBody();
	}

	@PostMapping(value = "getStudent")
	public String createStudent(@RequestBody Student student) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Student> entity = new HttpEntity<>(student, headers);
		return restTemplate.exchange(STUDENT_URL, HttpMethod.POST, entity, String.class).getBody();
	}

}
