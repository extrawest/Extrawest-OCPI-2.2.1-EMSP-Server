package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.enums.AuthMethod;
import com.extrawest.ocpi.model.enums.SessionStatus;
import com.extrawest.ocpi.model.vo.CdrToken;
import com.extrawest.ocpi.model.vo.ChargingPeriod;
import com.extrawest.ocpi.model.vo.Price;
import com.extrawest.ocpi.util.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO extends ClientOwnedObject implements OcpiRequestData, OcpiResponseData {
    @NotNull
    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    @JsonProperty("end_date_time")
    private LocalDateTime endDateTime;

    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = 4)
    @JsonProperty("kwh")
    private Float kwh;

    @NotNull
    @JsonProperty("cdr_token")
    private CdrToken cdrToken;

    @NotNull
    @JsonProperty("auth_method")
    private AuthMethod authMethod;

    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("authorization_reference")
    private String authorizationReference;

    @NotBlank
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("location_id")
    private String locationId;

    @NotBlank
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("evse_uid")
    private String evseUid;

    @NotBlank
    @Size(min = 1, max = 36)
    @Pattern(regexp = Constants.ASCII_REGEXP)
    @JsonProperty("connector_id")
    private String connectorId;

    @Size(min = 1, max = 255)
    @JsonProperty("meter_id")
    private String meterId;

    @NotBlank
    @Size(min = 1, max = 3)
    @JsonProperty("currency")
    private String currency;

    @JsonProperty("charging_periods")
    private List<ChargingPeriod> chargingPeriods;

    @JsonProperty("total_cost")
    private Price totalCost;

    @NotNull
    @JsonProperty("status")
    private SessionStatus status;
}
