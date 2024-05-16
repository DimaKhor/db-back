package com.trafficpolice.dbback.controller;

import com.trafficpolice.dbback.dto.HijackingsDTO;
import com.trafficpolice.dbback.service.HijackingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
