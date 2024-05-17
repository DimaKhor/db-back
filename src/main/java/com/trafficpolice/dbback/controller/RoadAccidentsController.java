package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.RoadAccidentsDTO;
import com.trafficpolice.dbback.service.RoadAccidentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
}
