package com.extrawest.ocpi.model.dto;

import com.extrawest.ocpi.model.dto.location.Image;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessDetails {

    /**
     * Name of the operator.
     */
    @NotBlank
    @Size(max = 100)
    @JsonProperty("name")
    private String name;
    /**
     * Link to the operator’s website.
     */
    @JsonProperty("website")
    private String website;
    /**
     * Image link to the operator’s logo.
     */
    @JsonProperty("logo")
    private Image logo;
}
