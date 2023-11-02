package com.extrawest.ocpi.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BusinessDetails{

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
