package com.extrawest.ocpi.model.dto.request;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.enums.CommandResultType;
import com.extrawest.ocpi.model.vo.DisplayText;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandResult implements OcpiRequestData {
    @NotBlank
    @JsonProperty("result")
    private CommandResultType result;
    @JsonProperty("message")
    private DisplayText message;
}
