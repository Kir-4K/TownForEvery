package com.ontravelsolutions.kostusev.townforevery.controller;

import com.ontravelsolutions.kostusev.townforevery.dto.TownInformationDto;
import com.ontravelsolutions.kostusev.townforevery.exception.TownInformationNotFoundException;
import com.ontravelsolutions.kostusev.townforevery.service.TownInformationService;
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
@RequestMapping(value = "/information")
public class TownInformationController {

    private static final String[] IGNORE_PROPERTIES = {"id", "town"};

    private final TownInformationService informationService;

    @Value("${response.entity.information.saved}")
    private String informationSaved;

    @Value("${response.entity.information.update}")
    private String informationUpdate;

    @Value("${response.entity.information.deleted}")
    private String informationDeleted;

    @GetMapping
    public List<TownInformationDto> findAll() {
        return informationService.findAll();
    }

    @GetMapping(value = "/{id}")
    public TownInformationDto getByName(@PathVariable("id") Long id) {
        return informationService.findById(id)
                .orElseThrow(TownInformationNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody TownInformationDto information) {
        informationService.save(information);
        return ResponseEntity.ok(informationSaved);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody TownInformationDto updatableInformation,
                                         @PathVariable("id") Long informationId) {
        TownInformationDto townInformation = informationService.findById(informationId)
                .orElseThrow(TownInformationNotFoundException::new);
        copyProperties(updatableInformation, townInformation, IGNORE_PROPERTIES);
        informationService.save(townInformation);
        return ResponseEntity.ok(informationUpdate);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long informationId) {
        TownInformationDto townInformation = informationService.findById(informationId)
                .orElseThrow(TownInformationNotFoundException::new);
        informationService.delete(townInformation);
        return ResponseEntity.ok(informationDeleted);
    }
}
