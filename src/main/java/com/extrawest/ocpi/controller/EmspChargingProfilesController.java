package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.charging_profile.ActiveChargingProfile;
import com.extrawest.ocpi.model.dto.charging_profile.results.AbstractProfileResult;
import com.extrawest.ocpi.service.EMSPChargingProfilesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emsp/api/2.2.1/chargingProfiles")
@Tag(name = "EmspChargingProfiles")
@Validated
public class EmspChargingProfilesController {

    protected final EMSPChargingProfilesService emspChargingProfilesService;

    protected EmspChargingProfilesController(@Autowired EMSPChargingProfilesService emspChargingProfilesService) {
        this.emspChargingProfilesService = emspChargingProfilesService;
    }

    /**
     * Receive the asynchronous response from the Charge Point.
     *
     * @param abstractProfileResult Choice: one of three
     *                              ActiveChargingProfileResult - Result of the GET ActiveChargingProfile request, from the Charge Point.
     *                              ChargingProfileResult Result of the PUT ChargingProfile request, from the Charge Point.
     *                              ClearProfileResult Result of the DELETE ChargingProfile request, from the Charge Point.
     * @return The response to the POST on the Sender interface SHALL contain the Response Format
     * with the data field omitted.
     */
    @PostMapping
    public ResponseEntity<ResponseFormat> postChargingProfile(
            @RequestBody AbstractProfileResult abstractProfileResult
    ) {
        return ResponseEntity.ok(emspChargingProfilesService.postChargingProfile(abstractProfileResult));
    }

    /**
     * Receiver (typically CPO) can send an updated ActiveChargingProfile when other inputs have made changes to
     * existing profile. When the Receiver (typically CPO) sends a update profile to the EVSE, for other reason
     * then the Sender (Typically SCSP) asking, the Sender SHALL post an update to this interface. When a local
     * input influence the ActiveChargingProfile in the EVSE AND the Receiver (typically CPO) is made aware of this,
     * the Receiver SHALL post an update to this interface.
     *
     * @param sessionId             The unique id that identifies the session in the CPO platform.
     * @param activeChargingProfile The new ActiveChargingProfile. If there is no longer any charging profile active,
     *                              the ActiveChargingProfile SHALL reflect this by showing the maximum charging
     *                              capacity of the EVSE.
     * @return The response to the PUT on the eMSP interface SHALL contain the Response Format with the data field omitted.
     */
    @PutMapping("/{session_id}")
    public ResponseEntity<ResponseFormat> putChargingProfile(
            @PathVariable(value = "session_id") @Size(min = 1, max = 36) String sessionId,
            @RequestBody @Valid ActiveChargingProfile activeChargingProfile
    ) {
        return ResponseEntity.ok(emspChargingProfilesService.putChargingProfile(sessionId, activeChargingProfile));
    }

}
