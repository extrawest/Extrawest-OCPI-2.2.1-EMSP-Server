package com.extrawest.ocpi.model.vo;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.enums.EnvironmentalImpactCategory;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
public class EnvironmentalImpact implements OcpiRequestData, OcpiResponseData {

    /**
     * The environmental impact category of this value.
     */
    @NotNull
    private EnvironmentalImpactCategory category;
    /**
     * Amount of this portion in g/kWh.
     */
    @NotNull
    @PositiveOrZero
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float amount;

}
