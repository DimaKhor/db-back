// PersonsController.java

package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.PersonsDTO;
import com.trafficpolice.dbback.service.PersonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
