package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.TransportTypesDTO;
import com.trafficpolice.dbback.entity.TransportTypes;
import com.trafficpolice.dbback.mapper.TransportTypesMapper;
import com.trafficpolice.dbback.repository.TransportTypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransportTypesService {

    private final TransportTypesRepository transportTypesRepository;
    private final TransportTypesMapper transportTypesMapper;

    public TransportTypesDTO findById(int id) {
        Optional<TransportTypes> transportTypes = transportTypesRepository.findById(id);
        if (transportTypes.isPresent()) {
            return transportTypesMapper.toDTO(transportTypes.get());
        } else {
            throw new RuntimeException("Transport type not found with id: " + id);
        }
    }

    public List<TransportTypesDTO> findAll() {
        List<TransportTypes> allTransportTypes = transportTypesRepository.findAll();
        return allTransportTypes.stream()
                .map(transportTypesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TransportTypesDTO save(TransportTypesDTO transportTypesDTO) {
        try {
            TransportTypes transportTypes = transportTypesMapper.toEntity(transportTypesDTO);
            TransportTypes savedTransportTypes = transportTypesRepository.save(transportTypes);
            return transportTypesMapper.toDTO(savedTransportTypes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save transport type: " + e.getMessage(), e);
        }
    }

    public TransportTypesDTO update(int id, TransportTypesDTO transportTypesDTO) {
        try {
            TransportTypes existingTransportTypes = transportTypesRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Transport type not found with id: " + id));
            existingTransportTypes.setName(transportTypesDTO.getName());
            TransportTypes updatedTransportTypes = transportTypesRepository.save(existingTransportTypes);
            return transportTypesMapper.toDTO(updatedTransportTypes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update transport type with id " + id + ": " + e.getMessage(), e);
        }
    }

    public void deleteById(int id) {
        try {
            if (transportTypesRepository.existsById(id)) {
                transportTypesRepository.deleteById(id);
            } else {
                throw new RuntimeException("Transport type not found with id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete transport type with id " + id + ": " + e.getMessage(), e);
        }
    }
}
