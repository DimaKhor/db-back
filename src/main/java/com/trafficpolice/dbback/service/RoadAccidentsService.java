package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.RoadAccidentsDTO;
import com.trafficpolice.dbback.entity.RoadAccidents;
import com.trafficpolice.dbback.mapper.RoadAccidentsMapper;
import com.trafficpolice.dbback.repository.RoadAccidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoadAccidentsService {

    private final RoadAccidentsRepository repository;
    private final RoadAccidentsMapper mapper;

    public RoadAccidentsDTO findById(int id) {
        Optional<RoadAccidents> accidents = repository.findById(id);
        if (accidents.isPresent()) {
            return mapper.toDTO(accidents.get());
        } else {
            throw new RuntimeException("Road accidents not found with id: " + id);
        }
    }

    public List<RoadAccidentsDTO> findAll() {
        List<RoadAccidents> accidentsList = repository.findAll();
        return accidentsList.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
