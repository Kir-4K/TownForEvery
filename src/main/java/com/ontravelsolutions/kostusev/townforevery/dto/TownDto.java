package com.ontravelsolutions.kostusev.townforevery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TownDto implements BaseDto {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;
}
