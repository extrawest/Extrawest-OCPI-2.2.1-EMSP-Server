package com.extrawest.ocpi.model.vo;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class DisplayText implements OcpiRequestData, OcpiResponseData {

    /**
     * Language Code ISO 639-1.
     */
    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 2)
    private String language;
    /**
     * Text to be displayed to end user. No markup, html etc. allowed.
     */
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 512)
    private String text;
}
