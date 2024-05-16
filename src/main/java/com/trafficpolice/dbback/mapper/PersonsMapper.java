package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.PersonsDTO;
import com.trafficpolice.dbback.entity.Persons;
import org.springframework.stereotype.Component;

@Component
public class PersonsMapper {

    public PersonsDTO toDTO(Persons person) {
        PersonsDTO personsDTO = new PersonsDTO();
        personsDTO.setId(person.getId());
        personsDTO.setLastname(person.getLastname());
        personsDTO.setName(person.getName());
        personsDTO.setFathername(person.getFathername());
        personsDTO.setCity(person.getCity());
        personsDTO.setStreet(person.getStreet());
        personsDTO.setHouse(person.getHouse());
        personsDTO.setApartment(person.getApartment());
        return personsDTO;
    }

    public Persons toEntity(PersonsDTO personDTO) {
        Persons person = new Persons();
        person.setId(personDTO.getId());
        person.setLastname(personDTO.getLastname());
        person.setName(personDTO.getName());
        person.setFathername(personDTO.getFathername());
        person.setCity(personDTO.getCity());
        person.setStreet(personDTO.getStreet());
        person.setHouse(personDTO.getHouse());
        person.setApartment(personDTO.getApartment());
        return person;
    }
}
