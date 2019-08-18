package com.ontravelsolutions.kostusev.townforevery.mapper;

import com.ontravelsolutions.kostusev.townforevery.dto.BaseDto;
import com.ontravelsolutions.kostusev.townforevery.entity.BaseEntity;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

    E dtoToEntity(D dto);

    D entityToDto(E entity);
}
