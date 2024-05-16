package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.TransportsDamagedInAccidentDTO;
import com.trafficpolice.dbback.entity.TransportsDamagedInAccident;
import com.trafficpolice.dbback.entity.TransportsDamagedInAccidentId;
import com.trafficpolice.dbback.mapper.TransportsDamagedInAccidentMapper;
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
}
