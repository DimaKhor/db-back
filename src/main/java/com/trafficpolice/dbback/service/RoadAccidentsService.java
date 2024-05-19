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

    public List<Object[]> getAccidentStatisticsByTypeAndPeriod(String typeName, String startDate, String endDate) {
        return repository.getAccidentStatisticsByTypeAndPeriod(typeName, startDate, endDate);
    }

    public List<String> findMostDangerousPlaces() {
        return repository.findMostDangerousPlaces();
    }

    public List<String> findMostPopularReason() {
        return repository.findMostPopularReason();
    }

    public List<Object[]> getDrunkDrivingAccidentsAndPercentage() {
        return repository.getDrunkDrivingAccidentsAndPercentage();
    }

    public List<String> getWantedVehicles() {
        return repository.getWantedVehicles();
    }

    public RoadAccidentsDTO addRoadAccident(RoadAccidentsDTO dto) {
        RoadAccidents accidents = mapper.toEntity(dto);
        accidents = repository.save(accidents);
        return mapper.toDTO(accidents);
    }

    public RoadAccidentsDTO updateRoadAccident(int id, RoadAccidentsDTO dto) {
        if (repository.existsById(id)) {
            RoadAccidents accidents = mapper.toEntity(dto);
            accidents.setId(id);
            accidents = repository.save(accidents);
            return mapper.toDTO(accidents);
        } else {
            throw new RuntimeException("Road accidents not found with id: " + id);
        }
    }

    public void deleteRoadAccident(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Road accidents not found with id: " + id);
        }
    }
}
