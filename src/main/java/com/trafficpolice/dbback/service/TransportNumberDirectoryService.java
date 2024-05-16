package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.TransportNumberDirectoryDTO;
import com.trafficpolice.dbback.entity.TransportNumberDirectory;
import com.trafficpolice.dbback.mapper.TransportNumberDirectoryMapper;
import com.trafficpolice.dbback.repository.TransportNumberDirectoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransportNumberDirectoryService {

    private final TransportNumberDirectoryRepository repository;
    private final TransportNumberDirectoryMapper mapper;

    public TransportNumberDirectoryDTO findById(int id) {
        Optional<TransportNumberDirectory> directory = repository.findById(id);
        if (directory.isPresent()) {
            return mapper.toDTO(directory.get());
        } else {
            throw new RuntimeException("Transport number directory not found with id: " + id);
        }
    }

    public List<TransportNumberDirectoryDTO> findAll() {
        List<TransportNumberDirectory> directories = repository.findAll();
        return directories.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public TransportNumberDirectoryDTO findByNumber(String number) {
        Optional<TransportNumberDirectory> directory = repository.findByNumber(number);
        if (directory.isPresent()) {
            return mapper.toDTO(directory.get());
        } else {
            throw new RuntimeException("Transport number directory not found with number: " + number);
        }
    }

    public List<TransportNumberDirectoryDTO> findByIssueDateBetween(Date startDate, Date endDate) {
        List<TransportNumberDirectory> directories = repository.findByIssueDateBetween(startDate, endDate);
        return directories.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
