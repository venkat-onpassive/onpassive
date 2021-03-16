package com.onpassive.omail.service;

import java.util.List;

import com.onpassive.omail.entity.Person;

public interface PersonService {

	String add(Person p);

	List<Person> retrieve();

	String remove(String cn);

	String update(Person person);

}
