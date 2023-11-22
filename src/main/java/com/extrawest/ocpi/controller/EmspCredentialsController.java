package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.CredentialsDto;
import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.EMSPCredentialsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emsp/api/2.2.1/credentials")
@Tag(name = "EmspCredentials")
@Validated
public class EmspCredentialsController {

    protected final EMSPCredentialsService emspCredentialsService;

    protected EmspCredentialsController(@Autowired EMSPCredentialsService emspCredentialsService) {
        this.emspCredentialsService = emspCredentialsService;
    }

    /**
     * Retrieves the credentials object to access the server’s platform.
     *
     * @return CredentialsDTO
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<CredentialsDto>> getCredentials() {
        CredentialsDto credentials = emspCredentialsService.getCredentials();

        ResponseFormat<CredentialsDto> responseFormat = new ResponseFormat<CredentialsDto>()
                .build(OcpiStatusCode.SUCCESS, credentials);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Provides the server with a credentials object to access the client’s system (i.e. register).
     *
     * @param credentialsToClient -  credentials to access the client’s system
     * @return current client's credentials to access the server’s system with newly generated token
     */
    @PostMapping
    public ResponseEntity<ResponseFormat<CredentialsDto>> postCredentials(
            @RequestBody @Valid CredentialsDto credentialsToClient) {

        CredentialsDto credentialsToServer = emspCredentialsService.postCredentials(credentialsToClient);

        ResponseFormat<CredentialsDto> responseFormat = new ResponseFormat<CredentialsDto>()
                .build(OcpiStatusCode.SUCCESS, credentialsToServer);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Provides the server with an updated credentials object to access the client’s system.
     *
     * @param credentialsDTO - credentials
     */
    @PutMapping
    public void putCredentials(@RequestBody @Valid CredentialsDto credentialsDTO) {
        emspCredentialsService.putCredentials(credentialsDTO);
    }

    /**
     * Informs the server that its credentials to the client’s system are now invalid (i.e. unregister).
     *
     * @param credentialsDTO - credentials
     */
    @DeleteMapping
    public void deleteCredentials(@RequestBody @Valid CredentialsDto credentialsDTO) {
        emspCredentialsService.deleteCredentials(credentialsDTO);
    }
}
