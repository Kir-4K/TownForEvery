package com.ontravelsolutions.kostusev.townforevery.mapper;

import com.ontravelsolutions.kostusev.townforevery.dto.TownDto;
import com.ontravelsolutions.kostusev.townforevery.entity.Town;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TownMapper extends BaseMapper<Town, TownDto> {
}
