package com.extrawest.ocpi.model.dto.response;

import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.enums.VersionNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VersionResponseDTO implements OcpiResponseData {
    @NotNull
    public VersionNumber version;
    @NotBlank
    @Size(max = 255)
    public String url;
}
