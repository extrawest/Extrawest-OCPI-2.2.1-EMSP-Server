package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.enums.AuthMethod;
import com.extrawest.ocpi.model.vo.*;
import com.extrawest.ocpi.util.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CdrDTO extends ClientOwnedObject implements OcpiRequestData, OcpiResponseData {
    @NotNull
    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;
    @NotNull
    @JsonProperty("end_date_time")
    private LocalDateTime endDateTime;
    @Size(min = 1, max = 36)
    @JsonProperty("session_id")
    private String sessionId;
    @NotNull
    @JsonProperty("cdr_token")
    private CdrToken cdrToken;
    @NotNull
    @JsonProperty("auth_method")
    private AuthMethod authMethod;
    @Size(min = 1, max = 36)
    @JsonProperty("authorization_reference")
    private String authorizationReference;
    @NotNull
    @JsonProperty("cdr_location")
    private CdrLocation cdrLocation;
    @JsonProperty("meter_id")
    private String meterId;
    @NotBlank
    @Size(max = 3)
    private String currency;
    private List<TariffDTO> tariffs;
    @NotEmpty
    @JsonProperty("charging_periods")
    private List<ChargingPeriod> chargingPeriods;
    @JsonProperty("signed_data")
    private SignedData signedData;
    @NotNull
    @JsonProperty("total_cost")
    private Price totalCost;
    @JsonProperty("total_fixed_cost")
    private Price totalFixedCost;
    @NotNull
    @JsonProperty("total_energy")
    private Float totalEnergy;
    @JsonProperty("total_energy_cost")
    private Price totalEnergyCost;
    @NotNull
    @JsonProperty("total_time")
    private Float totalTime;
    @JsonProperty("total_time_cost")
    private Price totalTimeCost;
    @JsonProperty("total_parking_time")
    private Float totalParkingTime;
    @JsonProperty("total_parking_cost")
    private Price totalParkingCost;
    @JsonProperty("total_reservation_cost")
    private Price totalReservationCost;
    @Size(min = 1, max = 255)
    private String remark;
    @Size(min = 1, max = 39)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("invoice_reference_id")
    private String invoiceReferenceId;
    private Boolean credit;
    @Size(min = 1, max = 39)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("credit_reference_id")
    private String creditReferenceId;
    @JsonProperty("home_charging_compensation")
    private Boolean homeChargingCompensation;
}
