package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.PersonsDTO;
import com.trafficpolice.dbback.service.PersonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonsController {

    private final PersonsService personsService;

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonsDTO> getPersonById(@PathVariable int id) {
        try {
            PersonsDTO personsDTO = personsService.findById(id);
            return ResponseEntity.ok(personsDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonsDTO>> getAllPersons() {
        try {
            List<PersonsDTO> personsDTOList = personsService.findAll();
            return ResponseEntity.ok(personsDTOList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //запрос 2
    // http://localhost:8080/persons/by-transport-number?number=ABC-123
    @GetMapping("/persons/by-transport-number")
    public ResponseEntity<PersonsDTO> getPersonByTransportNumber(@RequestParam String number) {
        try {
            PersonsDTO personsDTO = personsService.findByTransportNumber(number);
            return ResponseEntity.ok(personsDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/persons")
    public ResponseEntity<?> createPerson(@RequestBody PersonsDTO personsDTO) {
        try {
            PersonsDTO createdPerson = personsService.save(personsDTO);
            return ResponseEntity.ok(createdPerson);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create person: " + e.getMessage());
        }
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable int id, @RequestBody PersonsDTO personsDTO) {
        try {
            PersonsDTO updatedPerson = personsService.update(id, personsDTO);
            return ResponseEntity.ok(updatedPerson);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to update person with id " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id) {
        try {
            personsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to delete person with id " + id + ": " + e.getMessage());
        }
    }
}
