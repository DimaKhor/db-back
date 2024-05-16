package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.RoadAccidentsDTO;
import com.trafficpolice.dbback.service.RoadAccidentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoadAccidentsController {

    private final RoadAccidentsService service;

    @GetMapping("/roadaccidents/{id}")
    public ResponseEntity<RoadAccidentsDTO> getRoadAccidentsById(@PathVariable int id) {
        try {
            RoadAccidentsDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/roadaccidents")
    public ResponseEntity<List<RoadAccidentsDTO>> getAllRoadAccidents() {
        List<RoadAccidentsDTO> dtoList = service.findAll();
        return ResponseEntity.ok(dtoList);
    }
}
