package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.ResponseFormat;
import com.extrawest.ocpi.model.dto.SessionDTO;
import com.extrawest.ocpi.model.dto.TariffDTO;
import com.extrawest.ocpi.model.dto.response.VersionResponseDTO;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.EMSPSessionsService;
import com.extrawest.ocpi.validation.ClientObjectValidation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/emsp/api/2.2.1/sessions")
@Tag(name="EmspSessions")
@Validated
public class EMSPSessionsController {

    protected final EMSPSessionsService emspSessionsService;

    public EMSPSessionsController(@Autowired EMSPSessionsService emspSessionsService) {
        this.emspSessionsService = emspSessionsService;
    }

    /**
     * Retrieve a Session object from the eMSP’s system with Session.id equal to {session_id}.
     * @param countryCode Retrieve a Session object from the eMSP’s system with Session.id equal to {session_id}.
     * @param partyId Party ID (Provider ID) of the CPO performing the GET on the eMSP’s system.
     * @param sessionId id of the Session object to get from the eMSP’s system.
     * @return Requested Session object.
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<SessionDTO>> getSession(
            @RequestParam(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @RequestParam(value = "party_id") @Size(min = 3, max = 3) String partyId,
            @RequestParam(value = "tariff_id") @Size(min = 1, max = 36) String sessionId
    ) {
        SessionDTO sessionDto = emspSessionsService.getSession(countryCode, partyId, sessionId);

        ResponseFormat<SessionDTO> responseFormat = new ResponseFormat<SessionDTO>()
                .build(OcpiStatusCode.SUCCESS, sessionDto);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Send a new/updated Session object to the eMSP.
     * @param sessionDTO New or updated Session object.
     * @param countryCode Country code of the CPO performing this PUT on the eMSP’s system. This SHALL be the same
     * value as the country_code in the Session object being pushed.
     * @param partyId Party ID (Provider ID) of the CPO performing this PUT on the eMSP’s system. This SHALL be the
     * same value as the party_id in the Session object being pushed.
     * @param sessionId id of the new or updated Session object.
     */
    @PutMapping
    public ResponseEntity<ResponseFormat<SessionDTO>> putSession(
            @RequestBody @Valid SessionDTO sessionDTO,
            @RequestParam(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @RequestParam(value = "party_id") @Size(min = 3, max = 3) String partyId,
            @RequestParam(value = "tariff_id") @Size(min = 1, max = 36) String sessionId
    ) {
        ClientObjectValidation.checkClientCanModifyObject(sessionDTO, countryCode, partyId, sessionId);
        SessionDTO saved = emspSessionsService.putSession(sessionDTO, countryCode, partyId, sessionId);
        ResponseFormat<SessionDTO> responseFormat = new ResponseFormat<SessionDTO>()
                .build(OcpiStatusCode.SUCCESS, saved);

        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Send a new/updated Session object to the eMSP.
     * @param sessionDTO New or updated Session object.
     * @param countryCode Country code of the CPO performing this PUT on the eMSP’s system. This SHALL be the same
     * value as the country_code in the Session object being pushed.
     * @param partyId Party ID (Provider ID) of the CPO performing this PUT on the eMSP’s system. This SHALL be the
     * same value as the party_id in the Session object being pushed.
     * @param sessionId id of the new or updated Session object.
     */
    @PatchMapping
    public void patchSession(
            @RequestBody @Valid SessionDTO sessionDTO,
            @RequestParam(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @RequestParam(value = "party_id") @Size(min = 3, max = 3) String partyId,
            @RequestParam(value = "tariff_id") @Size(min = 1, max = 36) String sessionId
    ) {
        emspSessionsService.patchSession(sessionDTO, countryCode, partyId, sessionId);
    }


}
