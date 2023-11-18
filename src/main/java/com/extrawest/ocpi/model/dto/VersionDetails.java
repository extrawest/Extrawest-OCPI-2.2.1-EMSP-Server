package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.enums.VersionNumber;
import com.extrawest.ocpi.model.markers.OcpiResponseData;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDetails implements OcpiResponseData {
    /**
     * The version number.
     */
    @NotNull
    private VersionNumber version;

    /**
     * A list of supported endpoints for this version.
     */
    @NotEmpty
    private List<Endpoint> endpoints;
}
