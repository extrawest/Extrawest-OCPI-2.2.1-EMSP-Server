package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.vo.TariffRestrictions;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class TariffElementDTO implements OcpiRequestData, OcpiResponseData {
    /**
     * List of price components that describe the pricing of a tariff.
     */
    @NotEmpty
    @JsonProperty("price_components")
    private List<PriceComponentDTO> priceComponents;
    /**
     * Restrictions that describe the applicability of a tariff.
     */
    private TariffRestrictions restrictions;
}
