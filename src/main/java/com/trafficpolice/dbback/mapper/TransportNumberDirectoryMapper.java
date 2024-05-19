package com.trafficpolice.dbback.mapper;

import com.trafficpolice.dbback.dto.TransportNumberDirectoryDTO;
import com.trafficpolice.dbback.entity.TransportNumberDirectory;
import com.trafficpolice.dbback.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TransportNumberDirectoryMapper {

    @Autowired
    private TransportTypesRepository transportTypesRepository;

    @Autowired
    private PersonsRepository personsRepository;

    @Autowired
    private OrganizationsRepository organizationsRepository;

    @Autowired
    private BrandsRepository brandsRepository;

    @Autowired
    private ColorsRepository colorsRepository;

    public TransportNumberDirectoryDTO toDTO(TransportNumberDirectory directory) {
        TransportNumberDirectoryDTO dto = new TransportNumberDirectoryDTO();
        dto.setId(directory.getId());
        dto.setTransportTypeId(directory.getTransportType().getId());
        dto.setPersonId(directory.getPerson().getId());
        dto.setOrganizationId(directory.getOrganization() != null ?
                directory.getOrganization().getId() : null);
        dto.setBrandId(directory.getBrand().getId());
        dto.setColorId(directory.getColor().getId());
        dto.setIssueDate(directory.getIssueDate().toString());
        dto.setEngineCapacity(directory.getEngineCapacity());
        dto.setEngineId(directory.getEngineId());
        dto.setChassisId(directory.getChassisId());
        dto.setCoachbuilderId(directory.getCoachbuilderId());
        dto.setNumber(directory.getNumber());
        return dto;
    }

    public TransportNumberDirectory toEntity(TransportNumberDirectoryDTO dto) {
        TransportNumberDirectory directory = new TransportNumberDirectory();
        directory.setId(dto.getId());
        directory.setTransportType(transportTypesRepository.findById(dto.getTransportTypeId()).orElse(null));
        directory.setPerson(personsRepository.findById(dto.getPersonId()).orElse(null));
        Integer organizationId = dto.getOrganizationId();
        directory.setOrganization(organizationId != null ? organizationsRepository.findById(organizationId).orElse(null) : null);
        directory.setBrand(brandsRepository.findById(dto.getBrandId()).orElse(null));
        directory.setColor(colorsRepository.findById(dto.getColorId()).orElse(null));

        // Преобразование строки в объект Date
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date issueDate = dateFormat.parse(dto.getIssueDate());
            directory.setIssueDate(issueDate);
        } catch (ParseException e) {
            e.printStackTrace(); // или можно обработать исключение и выбросить новое исключение с понятным сообщением
        }

        directory.setEngineCapacity(dto.getEngineCapacity());
        directory.setEngineId(dto.getEngineId());
        directory.setChassisId(dto.getChassisId());
        directory.setCoachbuilderId(dto.getCoachbuilderId());
        directory.setNumber(dto.getNumber());

        return directory;
    }
}
