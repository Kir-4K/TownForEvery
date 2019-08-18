package com.ontravelsolutions.kostusev.townforevery.service;

import com.ontravelsolutions.kostusev.townforevery.dto.TownInformationDto;
import com.ontravelsolutions.kostusev.townforevery.entity.Town;
import com.ontravelsolutions.kostusev.townforevery.mapper.TownInformationMapper;
import com.ontravelsolutions.kostusev.townforevery.repository.TownInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PropertySource(value = "classpath:messages.properties")
@Service
@Transactional(readOnly = true)
public class TownInformationService {

    private static final Random RANDOM = new Random();
    private final TownInformationRepository townInformationRepository;
    private final TownInformationMapper mapper;

    @Value("${information.default.message}")
    private String defaultMessage;

    public Optional<TownInformationDto> findById(Long id) {
        return townInformationRepository.findById(id)
                .map(mapper::entityToDto);
    }

    public List<TownInformationDto> findAll() {
        return townInformationRepository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }

    public List<TownInformationDto> findAllByTown(Town town) {
        return townInformationRepository.findAllByTown(town)
                .stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }


    public List<TownInformationDto> findAllByTown(String town) {
        return townInformationRepository.findAllByTown_NameIgnoreCase(town)
                .stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }

    public TownInformationDto findRandomInformationByTown(List<TownInformationDto> information) {
        return Optional.of(information)
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(RANDOM.nextInt(information.size())))
                .orElse(getDefaultMessage());
    }

    private TownInformationDto getDefaultMessage() {
        return TownInformationDto.builder()
                .description(defaultMessage)
                .build();
    }

    @Transactional
    public void save(TownInformationDto information) {
        townInformationRepository.save(mapper.dtoToEntity(information));
    }

    @Transactional
    public void delete(TownInformationDto information) {
        townInformationRepository.delete(mapper.dtoToEntity(information));
    }
}
