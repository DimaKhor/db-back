package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.PersonsDTO;
import com.trafficpolice.dbback.entity.Persons;
import com.trafficpolice.dbback.mapper.PersonsMapper;
import com.trafficpolice.dbback.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonsService {

    private final PersonsRepository personsRepository;
    private final PersonsMapper personsMapper;

    public PersonsDTO findById(int id) {
        Optional<Persons> person = personsRepository.findById(id);
        return person.map(personsMapper::toDTO).orElse(null);
    }

    public List<PersonsDTO> findAll() {
        List<Persons> personsList = personsRepository.findAll();
        return personsList.stream()
                .map(personsMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonsDTO findByTransportNumber(String transportNumber) {
        Persons person = personsRepository.findByTransportNumber(transportNumber);
        if (person != null) {
            return personsMapper.toDTO(person);
        } else {
            return null;
        }
    }

    public PersonsDTO save(PersonsDTO personsDTO) {
        try {
            Persons person = personsMapper.toEntity(personsDTO);
            person = personsRepository.save(person);
            return personsMapper.toDTO(person);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save person: " + e.getMessage(), e);
        }
    }

    public PersonsDTO update(int id, PersonsDTO personsDTO) {
        try {
            Persons person = personsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));

            person.setLastname(personsDTO.getLastname());
            person.setName(personsDTO.getName());
            person.setFathername(personsDTO.getFathername());
            person.setCity(personsDTO.getCity());
            person.setStreet(personsDTO.getStreet());
            person.setHouse(personsDTO.getHouse());
            person.setApartment(personsDTO.getApartment());
            person = personsRepository.save(person);
            return personsMapper.toDTO(person);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update person with id " + id + ": " + e.getMessage(), e);
        }
    }

    public void deleteById(int id) {
        try {
            personsRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete person with id " + id + ": " + e.getMessage(), e);
        }
    }
}
