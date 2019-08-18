package com.ontravelsolutions.kostusev.townforevery.controller;

import com.ontravelsolutions.kostusev.townforevery.dto.TownDto;
import com.ontravelsolutions.kostusev.townforevery.exception.TownNotFoundException;
import com.ontravelsolutions.kostusev.townforevery.service.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PropertySource(value = "classpath:messages.properties")
@RestController
@RequestMapping(value = "/town")
public class TownController {

    private static final String[] IGNORE_PROPERTIES = {"id"};

    private final TownService townService;

    @Value("${response.entity.town.saved}")
    private String townSaved;

    @Value("${response.entity.town.update}")
    private String townUpdate;

    @Value("${response.entity.town.deleted}")
    private String townDeleted;

    @GetMapping
    public List<TownDto> findAll() {
        return townService.findAll();
    }

    @GetMapping(value = "/{id}")
    public TownDto getByName(@PathVariable("id") Long townId) {
        return townService.findById(townId)
                .orElseThrow(() -> new TownNotFoundException(townId));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody TownDto town) {
        townService.save(town);
        return ResponseEntity.ok(townSaved);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody TownDto updatableTown,
                                         @PathVariable("id") Long townId) {
        TownDto town = townService.findById(townId)
                .orElseThrow(() -> new TownNotFoundException(townId));
        copyProperties(updatableTown, town, IGNORE_PROPERTIES);
        townService.save(town);
        return ResponseEntity.ok(townUpdate);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long townId) {
        TownDto town = townService.findById(townId)
                .orElseThrow(() -> new TownNotFoundException(townId));
        townService.delete(town);
        return ResponseEntity.ok(townDeleted);
    }
}
