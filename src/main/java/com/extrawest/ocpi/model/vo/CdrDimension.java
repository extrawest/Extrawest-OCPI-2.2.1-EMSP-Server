package com.extrawest.ocpi.model.vo;

import com.extrawest.ocpi.model.enums.CdrDimensionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CdrDimension {

    /**
     * Type of CDR dimension.
     */
    @JsonProperty("type")
    @NotNull
    private CdrDimensionType type;

    /**
     * Volume of the dimension consumed, measured according to the dimension type.
     */
    @NotNull
    @JsonProperty("volume")
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float volume;
}
