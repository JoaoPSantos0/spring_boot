package br.com.erudio.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	private final AtomicLong counter = new AtomicLong();
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll(){
		
		logger.info("Finding all peaple!");
		return repository.findAll();
	}
	
	private Person mockPerson(int i) {
		
		return null;
	}

	public Person findById(Long id) {
		Person person = new Person();
		return repository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No records fouded for this ID!"));//novo metodo de tratar exceptions
		
	}
	
	public Person create(Person person) {
		
		logger.info("Creating new person!");
		
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating new person!");
		Person entity = repository.findById(person.getId())
			.orElseThrow(()-> new ResourceNotFoundException("No records fouded for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		logger.info("ting new person!");
		Person entity = repository.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("No records fouded for this ID!"));
		
		repository.delete(entity);
	}
}








