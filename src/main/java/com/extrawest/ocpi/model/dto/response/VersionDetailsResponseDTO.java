package com.extrawest.ocpi.model.dto.response;

import com.extrawest.ocpi.model.OcpiData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.enums.VersionNumber;
import com.extrawest.ocpi.model.vo.Endpoint;
import com.extrawest.ocpi.validation.ListOfAtLeastOneObjects;
import com.extrawest.ocpi.validation.RequiredValidator;
import com.extrawest.ocpi.validation.Validator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDetailsResponseDTO implements OcpiResponseData {
    @NotNull
    private VersionNumber version;
    @NotEmpty
    private List<Endpoint> endpoints;
}
