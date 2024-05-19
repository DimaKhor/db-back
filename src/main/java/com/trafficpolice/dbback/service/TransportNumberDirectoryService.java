package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.TransportNumberDirectoryDTO;
import com.trafficpolice.dbback.entity.TransportNumberDirectory;
import com.trafficpolice.dbback.mapper.TransportNumberDirectoryMapper;
import com.trafficpolice.dbback.repository.TransportNumberDirectoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransportNumberDirectoryService {

    private final TransportNumberDirectoryRepository repository;
    @Autowired
    private InspectionService inspectionService;
    private final TransportNumberDirectoryMapper mapper;

    public TransportNumberDirectoryDTO findById(int id) {
        Optional<TransportNumberDirectory> directory = repository.findById(id);
        return directory.map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Transport number directory not found with id: " + id));
    }

    public List<TransportNumberDirectoryDTO> findAll() {
        List<TransportNumberDirectory> directories = repository.findAll();
        return directories.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public TransportNumberDirectoryDTO findByNumber(String number) {
        Optional<TransportNumberDirectory> directory = repository.findByNumber(number);
        return directory.map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Transport number directory not found with number: " + number));
    }

    public List<TransportNumberDirectoryDTO> findByIssueDateBetween(Date startDate, Date endDate) {
        List<TransportNumberDirectory> directories = repository.findByIssueDateBetween(startDate, endDate);
        return directories.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Object[] getCarProfileByNumber(String number) {
        List<Integer> failedInspectionIds = inspectionService.findTransportIdsWithFailedInspection();
        return repository.getCarProfileByNumber(number, failedInspectionIds);
    }

    // Метод для создания записи
    public TransportNumberDirectoryDTO create(TransportNumberDirectoryDTO dto) {
        TransportNumberDirectory entity = mapper.toEntity(dto);
        TransportNumberDirectory savedEntity = repository.save(entity);
        return mapper.toDTO(savedEntity);
    }

    // Метод для обновления записи
    public TransportNumberDirectoryDTO update(int id, TransportNumberDirectoryDTO dto) {
        Optional<TransportNumberDirectory> optionalEntity = repository.findById(id);
        TransportNumberDirectory entity = optionalEntity.orElseThrow(() -> new RuntimeException("Transport number directory not found with id: " + id));
        entity.setTransportType(mapper.toEntity(dto).getTransportType());
        entity.setPerson(mapper.toEntity(dto).getPerson());
        entity.setOrganization(mapper.toEntity(dto).getOrganization());
        entity.setBrand(mapper.toEntity(dto).getBrand());
        entity.setColor(mapper.toEntity(dto).getColor());
        entity.setIssueDate(mapper.toEntity(dto).getIssueDate());
        entity.setEngineCapacity(mapper.toEntity(dto).getEngineCapacity());
        entity.setEngineId(mapper.toEntity(dto).getEngineId());
        entity.setChassisId(mapper.toEntity(dto).getChassisId());
        entity.setCoachbuilderId(mapper.toEntity(dto).getCoachbuilderId());
        entity.setNumber(mapper.toEntity(dto).getNumber());
        TransportNumberDirectory updatedEntity = repository.save(entity);
        return mapper.toDTO(updatedEntity);
    }

    public void deleteById(int id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete transport number with id " + id + ": " + e.getMessage());
        }
    }
}
