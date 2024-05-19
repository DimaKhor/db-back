package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.HijackingsDTO;
import com.trafficpolice.dbback.entity.Brands;
import com.trafficpolice.dbback.service.HijackingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListDataEvent;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HijackingsController {

    private final HijackingsService service;

    @GetMapping("/hijackings/{id}")
    public ResponseEntity<HijackingsDTO> getHijackingsById(@PathVariable int id) {
        try {
            HijackingsDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hijackings")
    public ResponseEntity<List<HijackingsDTO>> getAllHijackings() {
        try {
            List<HijackingsDTO> dtos = service.findAll();
            return ResponseEntity.ok(dtos);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //запрос 9
    @GetMapping("/hijackings/efficiency")
    public ResponseEntity<Double> getSearchEfficiency() {
        try {
            Double efficiency = service.getSearchEfficiency();
            return ResponseEntity.ok(efficiency);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //запрос 10
    // http://localhost:8080/hijackings/period?startDate=2021-01-01&endDate=2021-03-15
    @GetMapping("/hijackings/period")
    public ResponseEntity<List<String>> getHijackingsByPeriod(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            List<String> carNumbers = service.findHijackingsByPeriod(startDate, endDate);
            return ResponseEntity.ok(carNumbers);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/hijackings/period/count")
    public ResponseEntity<Integer> getHijackingsCountByPeriod(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            List<String> carNumbers = service.findHijackingsByPeriod(startDate, endDate);
            int hijackingsCount = carNumbers.size();
            return ResponseEntity.ok(hijackingsCount);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/hijackings/most_popular_signaling")
    public List<String> getMostPopularSignaling() {
        return service.findMostPopularSignaling();
    }

    @GetMapping("/most_stolen_brand")
    public List<String> getMostStolenBrand() {
        return service.findMostStolenBrandNames();
    }

    @PostMapping("/hijackings")
    public ResponseEntity<HijackingsDTO> addHijacking(@RequestBody HijackingsDTO dto) {
        try {
            HijackingsDTO savedDto = service.addHijacking(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/hijackings/{id}")
    public ResponseEntity<HijackingsDTO> updateHijacking(@PathVariable int id, @RequestBody HijackingsDTO dto) {
        try {
            HijackingsDTO updatedDto = service.updateHijacking(id, dto);
            return ResponseEntity.ok(updatedDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/hijackings/{id}")
    public ResponseEntity<Void> deleteHijacking(@PathVariable int id) {
        try {
            service.deleteHijacking(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
