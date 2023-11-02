package com.extrawest.ocpi.model;

import com.extrawest.ocpi.model.dto.TariffDTO;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFormat<T> {

    @JsonProperty("status_code")
    @NotNull
    @Min(value = 1000)
    @Max(value = 4999)
    private Integer statusCode;

    @JsonProperty("status_message")
    private String statusMessage;

    @NotNull
    private LocalDateTime timestamp;

    private T data;

    public ResponseFormat<T> build(OcpiStatusCode ocpiStatusCode) {
        this.setTimestamp(LocalDateTime.now());
        this.setStatusCode(ocpiStatusCode.getValue());
        this.setStatusMessage(ocpiStatusCode.getReasonPhrase());
        return this;
    }

    public ResponseFormat<T> build(OcpiStatusCode ocpiStatusCode, T data) {
        this.setData(data);
        this.setTimestamp(LocalDateTime.now());
        this.setStatusCode(ocpiStatusCode.getValue());
        this.setStatusMessage(ocpiStatusCode.getReasonPhrase());
        return this;
    }
}
