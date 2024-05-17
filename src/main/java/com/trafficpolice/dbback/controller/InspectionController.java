package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.InspectionDTO;
import com.trafficpolice.dbback.service.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
