package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.ColorsDTO;
import com.trafficpolice.dbback.entity.Colors;
import com.trafficpolice.dbback.mapper.ColorsMapper;
import com.trafficpolice.dbback.repository.ColorsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorsService {

    private final ColorsRepository colorsRepository;
    private final ColorsMapper colorsMapper;

    public ColorsDTO findById(int id) {
        Optional<Colors> colors = colorsRepository.findById(id);
        if (colors.isPresent()) return colorsMapper.toDTO(colors.get());
        else return null;
    }

    public List<ColorsDTO> findAll() {
        List<Colors> colorsList = colorsRepository.findAll();
        return colorsList.stream()
                .map(colorsMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ColorsDTO addColor(ColorsDTO colorsDTO) {
        try {
            Colors colors = colorsMapper.toEntity(colorsDTO);
            Colors savedColor = colorsRepository.save(colors);
            return colorsMapper.toDTO(savedColor);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add color: " + e.getMessage(), e);
        }
    }

    public void deleteColorById(int id) {
        try {
            if (!colorsRepository.existsById(id)) {
                throw new RuntimeException("Color with id " + id + " not found");
            }
            colorsRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete color: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Colors updateColor(int id, String name) {
        try {
            Colors color = colorsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Color not found"));
            color.setName(name);
            return colorsRepository.save(color);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update color: " + e.getMessage(), e);
        }
    }
}
