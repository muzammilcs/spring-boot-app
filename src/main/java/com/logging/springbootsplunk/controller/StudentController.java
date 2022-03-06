package com.logging.springbootsplunk.controller;


import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logging.springbootsplunk.model.Student;

@RestController
@RequestMapping("/student")

public class StudentController {
	
	private static final Map<Integer,Student> repo=new ConcurrentHashMap<>();
	
	private static final Logger log=LogManager.getLogger(StudentController.class);
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET )
	public Student getStudentDetails(@PathVariable("id") Integer id) {
		log.info("Logged Event id is "+id);
		
		if(repo.containsKey(id)) {
			return repo.get(id);
		}
		
		return new Student();
		
	}
	
	@GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> getStudent(){
		
		return repo.entrySet().stream().map(e->e.getValue()).collect(Collectors.toList());
		
	}
	
	
	@PostMapping(value="/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public Student saveStudent(@RequestBody Student student) {
		Integer id=new Random().nextInt(1000);
		student.setId(id);
		repo.put(id,student);
		return student;
	}

}
