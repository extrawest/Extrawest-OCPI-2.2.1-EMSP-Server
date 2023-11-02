package com.extrawest.ocpi.model.dto.response;

import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.Token;
import com.extrawest.ocpi.model.dto.TokenDTO;
import com.extrawest.ocpi.model.dto.request.LocationReferences;
import com.extrawest.ocpi.model.enums.AllowedType;
import com.extrawest.ocpi.model.vo.DisplayText;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationInfo implements OcpiResponseData {
    @NotNull
    @JsonProperty("allowed")
    private AllowedType allowed;
    @NotNull
    @JsonProperty("token")
    private TokenDTO token;
    @JsonProperty("location")
    private LocationReferences location;
    @Size(min = 1, max = 36)
    @JsonProperty("authorization_reference")
    private String authorizationReference;
    @JsonProperty("info")
    private DisplayText info;
}
