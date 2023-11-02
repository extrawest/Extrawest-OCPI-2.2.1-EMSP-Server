package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.enums.Facility;
import com.extrawest.ocpi.model.enums.ParkingType;
import com.extrawest.ocpi.model.vo.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO extends ClientOwnedObject implements LocationData {
    @NotNull
    private Boolean publish;
    @JsonProperty("publish_allowed_to")
    private List<PublishTokenType> publishAllowedTo;
    @Size(min = 1, max = 255)
    private String name;

    @NotBlank
    @Size(min = 1, max = 45)
    private String address;
    @NotBlank
    @Size(min = 1, max = 45)
    private String city;

    @Size(min = 1, max = 10)
    @JsonProperty("postal_code")
    private String postalCode;
    @Size(min = 1, max = 20)
    private String state;
    @NotBlank
    @Size(min = 1, max = 3)
    private String country;
    @NotNull
    private GeoLocation coordinates;
    @JsonProperty("related_locations")
    private List<AdditionalGeoLocation> relatedLocations;
    @JsonProperty("parking_type")
    private ParkingType parkingType;
    private List<Evse> evses;
    private List<DisplayText> directions;
    private BusinessDetails operator;
    @JsonProperty("suboperator")
    private BusinessDetails subOperator;
    private BusinessDetails owner;
    private List<Facility> facilities;
    @NotBlank
    @Size(min = 1, max = 255)
    @JsonProperty("time_zone")
    private String timeZone;
    @JsonProperty("opening_times")
    private Hours openingTimes;
    @JsonProperty("charging_when_closed")
    private Boolean chargingWhenClosed;
    private List<Image> images;
    @JsonProperty("energy_mix")
    private EnergyMix energyMix;
}
