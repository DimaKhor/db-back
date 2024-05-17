package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.PersonsDTO;
import com.trafficpolice.dbback.service.PersonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonsController {

    private final PersonsService personsService;

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonsDTO> getPersonById(@PathVariable int id) {
        PersonsDTO personsDTO = personsService.findById(id);
        return ResponseEntity.ok(personsDTO);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonsDTO>> getAllPersons() {
        List<PersonsDTO> personsDTOList = personsService.findAll();
        return ResponseEntity.ok(personsDTOList);
    }

    //запрос 2
    // http://localhost:8080/persons/by-transport-number?number=ABC-123
    @GetMapping("/persons/by-transport-number")
    public ResponseEntity<PersonsDTO> getPersonByTransportNumber(@RequestParam String number) {
        PersonsDTO personsDTO = personsService.findByTransportNumber(number);
        return ResponseEntity.ok(personsDTO);
    }
}
