package com.external.api.cardio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.external.api.cardio.modals.Person;
import com.external.api.cardio.repository.PersonRepository;

@Transactional
@Service
public class PersonService {

	private static final Logger LOGGER = Logger.getLogger(PersonService.class.getName());
	private PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public List<Person> findAll(String filterText) {
		if (filterText == null || filterText.isEmpty()) {
			return personRepository.findAll();
		} else {
			return personRepository.search(filterText);
		}
	}

	public long count() {
		return personRepository.count();
	}

	public void delete(Person person) {
		personRepository.delete(person);
	}

	public void save(Person person) {
		if (person == null) {
			LOGGER.log(Level.SEVERE, "Person is null. Are you sure you have connected your form to the application?");
			return;
		}
		personRepository.save(person);
	}

	public void updatePerson(long id, String Fname, String Lname, String phoneNumber, String email,
			LocalDateTime time) {
		Person p = personRepository.findById(id);
		p.setFirstName(Fname);
		p.setLastName(Lname);
		p.setPhoneNumber(phoneNumber);
		p.setEmail(email);
		p.setTime(time);
		personRepository.save(p);
	}

	@PostConstruct
	public void populateTestData() {
		if (personRepository.count() == 0) {
			personRepository.saveAll(Stream
					.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen", "Koen Johansen", "Alejandro Macdonald",
							"Angel Karlsson", "Yasir Gustavsson", "Hadi Svensson", "Emily Stewart", "Colette Davinci",
							"Rayan Davis", "Yurem Jackson", "Kelly Gustavsson")
					.map(name -> {
						
				        Random generator = new Random();        	        
				        int num1 = 0;  
				        int num2 = generator.nextInt(6)+1; 
				        int set3 = generator.nextInt(8999) + 100000;
				        
						String[] split = name.split(" ");
						Person person = new Person();
						person.setFirstName(split[0]);
						person.setLastName(split[1]);
						person.setPhoneNumber( num1 + "" + num2 + " " + set3);
						String email = (person.getFirstName() + "." + person.getLastName() + "@gmail.com")
								.toLowerCase();
						person.setEmail(email);
						person.setTime(LocalDateTime.now());
						return person;
					}).collect(Collectors.toList()));
		}
	}

}
