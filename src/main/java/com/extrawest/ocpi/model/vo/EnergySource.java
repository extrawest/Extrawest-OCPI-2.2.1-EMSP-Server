package com.extrawest.ocpi.model.vo;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.enums.EnergySourceCategory;
import com.extrawest.ocpi.validation.RequiredValidator;
import com.extrawest.ocpi.validation.Validatable;
import com.extrawest.ocpi.validation.Validator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
public class EnergySource implements OcpiRequestData, OcpiResponseData {
    /**
     * The type of energy source.
     */
    @JsonProperty("source")
    @NotNull
    private EnergySourceCategory source;

    /**
     * Percentage of this source (0-100) in the mix.
     */
    @JsonProperty("percentage")
    @NotNull
    @Min(0)
    @Max(100)
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float percentage;
}
