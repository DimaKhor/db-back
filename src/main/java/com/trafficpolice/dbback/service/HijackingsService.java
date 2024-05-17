package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.HijackingsDTO;
import com.trafficpolice.dbback.entity.Brands;
import com.trafficpolice.dbback.entity.Hijackings;
import com.trafficpolice.dbback.mapper.HijackingsMapper;
import com.trafficpolice.dbback.repository.HijackingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HijackingsService {

    private final HijackingsRepository repository;
    private final HijackingsMapper mapper;

    public HijackingsDTO findById(int id) {
        Optional<Hijackings> hijackings = repository.findById(id);
        if (hijackings.isPresent()) {
            return mapper.toDTO(hijackings.get());
        } else {
            throw new RuntimeException("Hijacking not found with id: " + id);
        }
    }

    public List<HijackingsDTO> findAll() {
        List<Hijackings> hijackings = repository.findAll();
        return hijackings.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Double getSearchEfficiency() {
        return repository.getSearchEfficiency();
    }

    public List<String> findHijackingsByPeriod(Date startDate, Date endDate) {
        return repository.findHijackingsByPeriod(startDate, endDate);
    }

    public List<String> findMostPopularSignaling() {
        return repository.findMostPopularSignaling();
    }

    public List<String> findMostStolenBrandNames() {
        return repository.findMostStolenBrandNames();
    }
}
