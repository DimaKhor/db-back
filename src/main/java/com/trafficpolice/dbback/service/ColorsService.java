package com.trafficpolice.dbback.service;

import com.trafficpolice.dbback.dto.ColorsDTO;
import com.trafficpolice.dbback.entity.Colors;
import com.trafficpolice.dbback.mapper.ColorsMapper;
import com.trafficpolice.dbback.repository.ColorsRepository;
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
}
