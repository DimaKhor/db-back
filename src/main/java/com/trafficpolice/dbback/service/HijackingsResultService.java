package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.HijackingsResultDTO;
import com.trafficpolice.dbback.entity.HijackingsResult;
import com.trafficpolice.dbback.mapper.HijackingsResultMapper;
import com.trafficpolice.dbback.repository.HijackingsResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HijackingsResultService {

    private final HijackingsResultRepository repository;
    private final HijackingsResultMapper mapper;

    public HijackingsResultDTO findById(int id) {
        Optional<HijackingsResult> hijackingsResult = repository.findById(id);
        if (hijackingsResult.isPresent()) {
            return mapper.toDTO(hijackingsResult.get());
        } else {
            throw new RuntimeException("Hijacking result not found with id: " + id);
        }
    }

    public List<HijackingsResultDTO> findAll() {
        List<HijackingsResult> hijackingsResults = repository.findAll();
        return hijackingsResults.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public HijackingsResultDTO create(HijackingsResultDTO dto) {
        HijackingsResult hijackingsResult = mapper.toEntity(dto);
        HijackingsResult savedHijackingsResult = repository.save(hijackingsResult);
        return mapper.toDTO(savedHijackingsResult);
    }

    public HijackingsResultDTO update(int id, HijackingsResultDTO dto) {
        Optional<HijackingsResult> optionalHijackingsResult = repository.findById(id);
        if (optionalHijackingsResult.isPresent()) {
            HijackingsResult hijackingsResult = optionalHijackingsResult.get();
            hijackingsResult.setResultName(dto.getResultName());
            HijackingsResult updatedHijackingsResult = repository.save(hijackingsResult);
            return mapper.toDTO(updatedHijackingsResult);
        } else {
            throw new RuntimeException("Hijacking result not found with id: " + id);
        }
    }

    public void deleteById(int id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete hijacking result with id " + id + ": " + e.getMessage());
        }
    }
}
