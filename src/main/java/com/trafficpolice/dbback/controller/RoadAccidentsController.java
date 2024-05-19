package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.RoadAccidentsDTO;
import com.trafficpolice.dbback.service.RoadAccidentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    //запрос 5
    @GetMapping("/accident-statistics")
    public ResponseEntity<?> getAccidentStatisticsByTypeAndPeriod(
            @RequestParam("type") String accidentType,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        try {
            return ResponseEntity.ok(service.getAccidentStatisticsByTypeAndPeriod(accidentType, startDate, endDate));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //запросы 6
    @GetMapping("/most-dangerous-places")
    public ResponseEntity<List<String>> findMostDangerousPlace() {
        List<String> mostDangerousPlaces = service.findMostDangerousPlaces();
        return ResponseEntity.ok(mostDangerousPlaces);
    }

    @GetMapping("/most-popular-reasons")
    public ResponseEntity<List<String>> findMostPopularReason() {
        List<String> mostPopularReasons = service.findMostPopularReason();
        return ResponseEntity.ok(mostPopularReasons);
    }

    //запрос 7
    @GetMapping("/drunk-driving-accidents")
    public ResponseEntity<List<Object[]>> getDrunkDrivingAccidentsAndPercentage() {
        List<Object[]> drunkDrivingAccidents = service.getDrunkDrivingAccidentsAndPercentage();
        return ResponseEntity.ok(drunkDrivingAccidents);
    }

    //запрос 8
    @GetMapping("/wanted-vehicles")
    public ResponseEntity<List<String>> getWantedVehicles() {
        return ResponseEntity.ok(service.getWantedVehicles());
    }

    @PostMapping("/roadaccidents")
    public ResponseEntity<RoadAccidentsDTO> addRoadAccident(@RequestBody RoadAccidentsDTO dto) {
        try {
            RoadAccidentsDTO savedDto = service.addRoadAccident(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/roadaccidents/{id}")
    public ResponseEntity<RoadAccidentsDTO> updateRoadAccident(@PathVariable int id, @RequestBody RoadAccidentsDTO dto) {
        try {
            RoadAccidentsDTO updatedDto = service.updateRoadAccident(id, dto);
            return ResponseEntity.ok(updatedDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/roadaccidents/{id}")
    public ResponseEntity<Void> deleteRoadAccident(@PathVariable int id) {
        try {
            service.deleteRoadAccident(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
