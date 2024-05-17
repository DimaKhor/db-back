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
}
