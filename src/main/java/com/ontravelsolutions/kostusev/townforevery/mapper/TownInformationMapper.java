package com.ontravelsolutions.kostusev.townforevery.mapper;

import com.ontravelsolutions.kostusev.townforevery.dto.TownInformationDto;
import com.ontravelsolutions.kostusev.townforevery.entity.TownInformation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TownInformationMapper extends BaseMapper<TownInformation, TownInformationDto> {
}
