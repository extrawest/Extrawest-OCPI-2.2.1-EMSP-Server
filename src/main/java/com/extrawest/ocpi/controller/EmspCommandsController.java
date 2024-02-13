package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.command.CommandResult;
import com.extrawest.ocpi.model.enums.CommandType;
import com.extrawest.ocpi.service.EMSPCommandsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emsp/api/2.2.1/commands")
@Tag(name = "EmspCommands")
@Validated
public class EmspCommandsController {

    protected final EMSPCommandsService emspCommandsService;

    protected EmspCommandsController(@Autowired EMSPCommandsService emspCommandsService) {
        this.emspCommandsService = emspCommandsService;
    }

    /**
     * It is up to the implementation of the eMSP to determine what parameters are put in the URL.
     * The eMSP sends a URL in the POST method body to the CPO. The CPO is required to use this URL for
     * the asynchronous response by the Charge Point. It is advised to make this URL unique for
     * every request to differentiate simultaneous commands, for example by adding a unique id as a URL segment.
     * @param commandResult Result of the command request, from the Charge Point.
     * @param commandType type of command
     * @param uniqueId It is advised to make this URL unique for every request to differentiate simultaneous commands,
     * for example by adding a unique id as a URL segment.
     */
    @PostMapping("/{command}/{uid}")
    public void postCommandResult(
            @RequestBody @Valid CommandResult commandResult,
            @PathVariable(value = "command") CommandType commandType,
            @PathVariable(value = "uid") @Size(min = 1, max = 36) String uniqueId
    ) {
        emspCommandsService.postCommandResult(commandResult, commandType, uniqueId);
    }

}
