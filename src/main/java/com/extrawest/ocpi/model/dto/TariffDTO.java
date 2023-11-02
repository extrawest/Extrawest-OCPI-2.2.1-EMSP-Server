package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.enums.TariffType;
import com.extrawest.ocpi.model.vo.DisplayText;
import com.extrawest.ocpi.model.vo.EnergyMix;
import com.extrawest.ocpi.model.vo.Price;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TariffDTO extends ClientOwnedObject implements OcpiRequestData, OcpiResponseData {
    @NotBlank
    @Size(min = 1, max = 3)
    private String currency;
    private TariffType type;
    @JsonProperty("tariff_alt_text")
    private List<DisplayText> tariffAltText;
    @JsonProperty("tariff_alt_url")
    private String tariffAltUrl;
    @JsonProperty("min_price")
    private Price minPrice;
    @JsonProperty("max_price")
    private Price maxPrice;

    @NotEmpty
    private List<TariffElementDTO> elements;

    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;
    @JsonProperty("end_date_time")
    private LocalDateTime endDateTime;
    @JsonProperty("energy_mix")
    private EnergyMix energyMix;
}
