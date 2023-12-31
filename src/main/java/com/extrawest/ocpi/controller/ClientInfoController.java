package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ClientInfoDto;
import com.extrawest.ocpi.service.ClientInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/2.2.1/hubClientInfo")
@Tag(name = "ClientInfo")
@Validated
public class ClientInfoController {

    protected final ClientInfoService clientInfoService;

    protected ClientInfoController(@Autowired ClientInfoService clientInfoService) {
        this.clientInfoService = clientInfoService;
    }

    /**
     * Retrieve a ClientInfo object as it is stored in the connected client's system.
     *
     * @param countryCode Country code of the requested ClientInfo object.
     * @param partyId     Party ID (Provider ID) of the requested ClientInfo object.
     * @return The requested ClientInfo object.
     */
    @GetMapping("/{country_code}/{party_id}")
    public ResponseEntity<ClientInfoDto> getHubClientInfo(
            @PathVariable(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @PathVariable(value = "party_id") @Size(min = 3, max = 3) String partyId
    ) {
        return ResponseEntity.ok(clientInfoService.getHubClientInfo(countryCode, partyId));
    }

    /**
     * Push new/updated ClientInfo object to the connect client.
     * @param countryCode Country code of the eMSP sending this PUT request to the CPO system.
     * @param partyId Party ID (Provider ID) of the eMSP sending this PUT request to the CPO system.
     */
    @PutMapping("/{country_code}/{party_id}")
    public void putHubClientInfo(
            @PathVariable(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @PathVariable(value = "party_id") @Size(min = 3, max = 3) String partyId
    ) {
        clientInfoService.putHubClientInfo(countryCode, partyId);
    }

}
