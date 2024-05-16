package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.InspectionDTO;
import com.trafficpolice.dbback.entity.Inspection;
import com.trafficpolice.dbback.mapper.InspectionMapper;
import com.trafficpolice.dbback.repository.InspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InspectionService {

    private final InspectionRepository repository;
    private final InspectionMapper mapper;

    public InspectionDTO findById(int id) {
        Optional<Inspection> inspection = repository.findById(id);
        if (inspection.isPresent()) {
            return mapper.toDTO(inspection.get());
        } else {
            throw new RuntimeException("Inspection not found with id: " + id);
        }
    }

    public List<InspectionDTO> findAll() {
        List<Inspection> inspections = repository.findAll();
        return inspections.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
