package com.extrawest.ocpi.model.vo;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
public class Price implements OcpiRequestData, OcpiResponseData {
    /**
     * Price/Cost excluding VAT.
     */
    @NotNull
    @JsonProperty("excl_vat")
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float exclVat;
    /**
     * Price/Cost including VAT.
     */
    @NotNull
    @JsonProperty("incl_vat")
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    private Float inclVat;
}
