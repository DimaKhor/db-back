package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.InspectionDTO;
import com.trafficpolice.dbback.service.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InspectionController {

    private final InspectionService service;

    @GetMapping("/inspection/{id}")
    public ResponseEntity<InspectionDTO> getInspectionById(@PathVariable int id) {
        try {
            InspectionDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/inspection")
    public ResponseEntity<List<InspectionDTO>> getAllInspections() {
        try {
            List<InspectionDTO> dtos = service.findAll();
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //запрос 4
    @GetMapping("/failed-inspection")
    public ResponseEntity<List<Integer>> getTransportIdsWithFailedInspection() {
        List<Integer> transportIds = service.findTransportIdsWithFailedInspection();
        return ResponseEntity.ok(transportIds);
    }

    //запрос 4
    @GetMapping("/failed-inspection-count-owners")
    public ResponseEntity<Integer> countOwnersWithFailedInspection() {
        int count = service.countOwnersWithFailedInspection();
        return ResponseEntity.ok(count);
    }

    @PostMapping("/inspection")
    public ResponseEntity<InspectionDTO> addInspection(@RequestBody InspectionDTO dto) {
        try {
            InspectionDTO savedDto = service.addInspection(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/inspection/{id}")
    public ResponseEntity<InspectionDTO> updateInspection(@PathVariable int id, @RequestBody InspectionDTO dto) {
        try {
            InspectionDTO updatedDto = service.updateInspection(id, dto);
            return ResponseEntity.ok(updatedDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/inspection/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable int id) {
        try {
            service.deleteInspection(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
