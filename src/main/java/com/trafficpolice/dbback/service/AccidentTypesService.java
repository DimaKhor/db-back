package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.AccidentTypesDTO;
import com.trafficpolice.dbback.entity.AccidentTypes;
import com.trafficpolice.dbback.mapper.AccidentTypesMapper;
import com.trafficpolice.dbback.repository.AccidentTypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccidentTypesService {

    private final AccidentTypesRepository repository;
    private final AccidentTypesMapper mapper;

    public AccidentTypesDTO findById(int id) {
        Optional<AccidentTypes> accidentTypes = repository.findById(id);
        if (accidentTypes.isPresent()) {
            return mapper.toDTO(accidentTypes.get());
        } else {
            throw new RuntimeException("Accident type not found with id: " + id);
        }
    }

    public List<AccidentTypesDTO> findAll() {
        List<AccidentTypes> accidentTypesList = repository.findAll();
        return accidentTypesList.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
