package com.extrawest.ocpi.model.vo;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.enums.ImageCategory;
import com.extrawest.ocpi.validation.*;
import com.extrawest.ocpi.validation.*;
import com.extrawest.ocpi.validation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
public class Image implements OcpiResponseData, OcpiRequestData {

    /**
     * URL from where the image data can be fetched through a web browser.
     */
    @NotBlank
    @JsonProperty("url")
    private String url;
    /**
     * URL from where a thumbnail of the image can be fetched through a
     * webbrowser.
     */
    @JsonProperty("thumbnail")
    private String thumbnail;
    /**
     * Describes what the image is used for.
     */
    @NotNull
    @JsonProperty("category")
    private ImageCategory category;
    /**
     * Image type like: gif, jpeg, png, svg.
     */
    @NotBlank
    @Size(max = 4)
    @JsonProperty("type")
    private String type;
    /**
     * Width of the full scale image.
     */
    @Max(5)
    @JsonProperty("width")
    private String width;
    /**
     * Height of the full scale image.
     */
    @Max(5)
    @JsonProperty("height")
    private String height;
}
