package com.extrawest.ocpi.model.vo;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Information about a energy contract that belongs to a Token so a driver could use his/her own energy contract
 * when charging at a Charge Point.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyContract implements OcpiRequestData, OcpiResponseData {
    /**
     * Name of the energy supplier for this token.
     */
    @NotNull
    @Size(max = 61)
    @JsonProperty("supplier_name")
    private String supplierName;

    @JsonProperty("contract_id")
    private String contractId;
}
