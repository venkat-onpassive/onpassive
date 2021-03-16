package com.onpassive.omail.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.omail.entity.Person;
import com.onpassive.omail.service.PersonService;


@RestController
public class SampleController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/hai")
	public String authenticate() {
		System.err.println("username");
		
		System.err.println("Its Error Message");
		return "Succes ";
	}
	@PostMapping("/add-user")
	public ResponseEntity<String> bindLdapPerson(@RequestBody Person person) {
		 String User = personService.add(person);
		return new ResponseEntity<>( User,HttpStatus.OK);
	}

	@GetMapping("/retrieve-users")
	public ResponseEntity<?> retrieve() {
		
		List<Person> retrieve = personService.retrieve();
		return new ResponseEntity<List<Person>>(retrieve, HttpStatus.OK);
	}
	
	@GetMapping("/remove-user")
	public ResponseEntity<String> unbindLdapPerson(@RequestParam(name = "cn") String cn) {
		 String result = personService.remove(cn);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@PutMapping("/update-user")
	public ResponseEntity<String> rebindLdapPerson(@RequestBody Person person) {
		String result = personService.update(person);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
