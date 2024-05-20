package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.TransportsDamagedInAccidentDTO;
import com.trafficpolice.dbback.entity.RoadAccidents;
import com.trafficpolice.dbback.entity.TransportNumberDirectory;
import com.trafficpolice.dbback.entity.TransportsDamagedInAccident;
import com.trafficpolice.dbback.entity.TransportsDamagedInAccidentId;
import com.trafficpolice.dbback.mapper.TransportsDamagedInAccidentMapper;
import com.trafficpolice.dbback.repository.RoadAccidentsRepository;
import com.trafficpolice.dbback.repository.TransportNumberDirectoryRepository;
import com.trafficpolice.dbback.repository.TransportsDamagedInAccidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransportsDamagedInAccidentService {

    private final TransportsDamagedInAccidentRepository repository;
    private final RoadAccidentsRepository roadAccidentsRepository;
    private final TransportNumberDirectoryRepository transportNumberDirectoryRepository;
    private final TransportsDamagedInAccidentMapper mapper;

    public TransportsDamagedInAccidentDTO findById(int accidentId, int transportId) {
        Optional<TransportsDamagedInAccident> transportsDamagedInAccident = repository.findById(new TransportsDamagedInAccidentId(accidentId, transportId));
        if (transportsDamagedInAccident.isPresent()) {
            return mapper.toDTO(transportsDamagedInAccident.get());
        } else {
            throw new RuntimeException("Transports damaged in accident not found with accident id: " + accidentId + " and transport id: " + transportId);
        }
    }

    public List<TransportsDamagedInAccidentDTO> findByAccidentId(int accidentId) {
        List<TransportsDamagedInAccident> transportsDamagedInAccidents = repository.findAll().stream()
                .filter(t -> t.getId().getAccidentId() == accidentId)
                .collect(Collectors.toList());
        return transportsDamagedInAccidents.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TransportsDamagedInAccidentDTO> findByTransportId(int transportId) {
        List<TransportsDamagedInAccident> transportsDamagedInAccidents = repository.findAll().stream()
                .filter(t -> t.getId().getTransportId() == transportId)
                .collect(Collectors.toList());
        return transportsDamagedInAccidents.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TransportsDamagedInAccidentDTO> findAll() {
        List<TransportsDamagedInAccident> allTransportsDamagedInAccidents = repository.findAll();
        return allTransportsDamagedInAccidents.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public TransportsDamagedInAccidentDTO addTransportsDamagedInAccident(TransportsDamagedInAccidentDTO dto) {
        TransportsDamagedInAccidentId id = new TransportsDamagedInAccidentId(dto.getAccidentId(), dto.getTransportId());
        if (repository.existsById(id)) {
            throw new RuntimeException("Record with the same primary key already exists.");
        }

        RoadAccidents roadAccident = roadAccidentsRepository.findById(dto.getAccidentId())
                .orElseThrow(() -> new RuntimeException("RoadAccident not found with id: " + dto.getAccidentId()));
        TransportNumberDirectory transportNumberDirectory = transportNumberDirectoryRepository.findById(dto.getTransportId())
                .orElseThrow(() -> new RuntimeException("TransportNumberDirectory not found with id: " + dto.getTransportId()));

        TransportsDamagedInAccident transportsDamagedInAccident = mapper.toEntity(dto, roadAccident, transportNumberDirectory);
        TransportsDamagedInAccident savedEntity = repository.save(transportsDamagedInAccident);
        return mapper.toDTO(savedEntity);
    }

    public TransportsDamagedInAccidentDTO updateTransportsDamagedInAccident(int accidentId, int transportId, TransportsDamagedInAccidentDTO dto) {
        TransportsDamagedInAccidentId id = new TransportsDamagedInAccidentId(accidentId, transportId);
        TransportsDamagedInAccident existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transports damaged in accident not found with accident id: " + accidentId + " and transport id: " + transportId));

        existingEntity.setDidRunAway(dto.isDidRunAway());
        existingEntity.setFound(dto.getFound());

        TransportsDamagedInAccident updatedEntity = repository.save(existingEntity);
        return mapper.toDTO(updatedEntity);
    }



    public void deleteTransportsDamagedInAccident(int accidentId, int transportId) {
        TransportsDamagedInAccidentId id = new TransportsDamagedInAccidentId(accidentId, transportId);
        repository.deleteById(id);
    }
}
