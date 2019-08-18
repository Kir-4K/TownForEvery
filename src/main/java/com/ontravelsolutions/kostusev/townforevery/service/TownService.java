package com.ontravelsolutions.kostusev.townforevery.service;

import com.ontravelsolutions.kostusev.townforevery.dto.TownDto;
import com.ontravelsolutions.kostusev.townforevery.mapper.TownMapper;
import com.ontravelsolutions.kostusev.townforevery.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional(readOnly = true)
public class TownService {

    private final TownRepository townRepository;
    private final TownMapper mapper;

    public Optional<TownDto> findById(Long id) {
        return townRepository.findById(id)
                .map(mapper::entityToDto);
    }

    public Optional<TownDto> findByName(String name) {
        return townRepository.findByNameIgnoreCase(name)
                .map(mapper::entityToDto);
    }

    public List<TownDto> findAll() {
        return townRepository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    public void save(TownDto town) {
        townRepository.save(mapper.dtoToEntity(town));
    }

    @Transactional
    public void delete(TownDto town) {
        townRepository.delete(mapper.dtoToEntity(town));
    }
}
