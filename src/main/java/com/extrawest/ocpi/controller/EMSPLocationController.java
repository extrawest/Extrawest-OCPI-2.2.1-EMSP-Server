package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.ResponseFormat;
import com.extrawest.ocpi.model.dto.LocationDTO;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.model.vo.Connector;
import com.extrawest.ocpi.model.vo.Evse;
import com.extrawest.ocpi.model.vo.LocationData;
import com.extrawest.ocpi.service.EMSPLocationService;
import com.extrawest.ocpi.validation.ClientObjectValidation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emsp/api/2.2.1/locations")
@Tag(name="EmspLocation")
public class EMSPLocationController {

    protected final EMSPLocationService emspLocationService;

    public EMSPLocationController(@Autowired EMSPLocationService emspLocationService) {
        this.emspLocationService = emspLocationService;
    }

    /**
     * Retrieve a Location as it is stored in the eMSP system.
     * @param countryCode Country code of the CPO requesting data from the eMSP system.
     * @param partyId Party ID (Provider ID) of the CPO requesting data from the eMSP system.
     * @param locationId Location.id of the Location object to retrieve.
     * @return The response contains the requested object:
     *      Location - If a Location object was requested: the Location object.
     *      EVSE - If an EVSE object was requested: the EVSE object.
     *      Connector - If a Connector object was requested: the Connector object.
     */
    @GetMapping("/{country_code}/{party_id}/{location_id}")
    public ResponseEntity<ResponseFormat<LocationData>> getLocation(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId
    ) {
        LocationData locationData = emspLocationService.getLocation(countryCode, partyId, locationId);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/{country_code}/{party_id}/{location_id}/{evse_uid}")
    public ResponseEntity<ResponseFormat<LocationData>> getEvse(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String party_id,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid
    ) {
        LocationData locationData = emspLocationService.getEvse(countryCode, party_id, locationId, evseUid);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}")
    public ResponseEntity<ResponseFormat<LocationData>> getConnector(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String party_id,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid,
            @PathVariable(value = "connector_id") String connectorId
    ) {
        LocationData locationData = emspLocationService.getConnector(countryCode, party_id, locationId, evseUid, connectorId);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }


    @PutMapping("/{country_code}/{party_id}/{location_id}")
    public ResponseEntity<ResponseFormat<LocationData>> pushLocation(
            @RequestBody @Valid LocationDTO locationDTO,
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId
    ) {
        ClientObjectValidation.checkClientCanModifyObject(locationDTO, countryCode, partyId, locationId);
        LocationData locationData = emspLocationService.pushLocation(locationDTO, countryCode, partyId, locationId);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * The CPO pushes available Location, EVSE or Connector objects to the eMSP. PUT can be used to send
     * new Location objects to the eMSP but also to replace existing Locations.
     *
     * @param evse The request body contains the new/updated object.
     * @param countryCode Country code of the CPO requesting this PUT to the eMSP system.
     *                    This SHALL be the same value as the country_code in the Location object being pushed.
     * @param partyId     Party ID (Provider ID) of the CPO requesting this PUT to the eMSP system.
     *                    This SHALL be the same value as the party_id in the Location object being pushed.
     * @param locationId  Location.id of the new Location object, or the Location of which an EVSE or
     *                    Connector object is pushed.
     * @param evseUid     Evse.uid, required when an EVSE or Connector object is pushed.
     */
    @PutMapping("/{country_code}/{party_id}/{location_id}/{evse_uid}")
    public ResponseEntity<ResponseFormat<LocationData>> pushEvse(
            @RequestBody @Valid Evse evse,
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid
    ) {
        ClientObjectValidation.checkClientCanModifyObject(evse, evseUid);
        LocationData locationData = emspLocationService.pushEvse(evse, countryCode, partyId, locationId, evseUid);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }

    @PutMapping("/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}")
    public ResponseEntity<ResponseFormat<LocationData>> pushConnector(
            @RequestBody @Valid Connector connector,
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid,
            @PathVariable(value = "connector_id") String connectorId
    ) {
        ClientObjectValidation.checkClientCanModifyObject(connector, connectorId);
        LocationData locationData = emspLocationService.pushConnector(connector, countryCode, partyId, locationId, evseUid, connectorId);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * The CPO pushes available Location, EVSE or Connector objects to the eMSP. PUT can be used to send
     * new Location objects to the eMSP but also to replace existing Locations.
     *
     * @param locationDTO The request body contains the new/updated object.
     * @param countryCode Country code of the CPO requesting this PUT to the eMSP system.
     *                    This SHALL be the same value as the country_code in the Location object being pushed.
     * @param partyId     Party ID (Provider ID) of the CPO requesting this PUT to the eMSP system.
     *                    This SHALL be the same value as the party_id in the Location object being pushed.
     * @param locationId  Location.id of the new Location object, or the Location of which an EVSE or
     *                    Connector object is pushed.
     * @param evseUid     Evse.uid, required when an EVSE or Connector object is pushed.
     * @param connectorId Connector.id, required when a Connector object is pushed.
     */
    @PatchMapping
    public ResponseEntity<ResponseFormat<LocationData>> patchLocation(
            @RequestBody @Valid LocationDTO locationDTO,
            @RequestParam(value = "country_code") String countryCode,
            @RequestParam(value = "party_id") String partyId,
            @RequestParam(value = "location_id") String locationId,
            @RequestParam(value = "evse_uid", required = false) String evseUid,
            @RequestParam(value = "connector_id", required = false) String connectorId
    ) {
        ClientObjectValidation.checkClientCanModifyObject(locationDTO, countryCode, partyId, locationId);
        LocationData locationData = emspLocationService.patchLocation(locationDTO, countryCode, partyId, locationId, evseUid, connectorId);

        ResponseFormat<LocationData> responseFormat = new ResponseFormat<LocationData>()
                .build(OcpiStatusCode.SUCCESS, locationData);
        return ResponseEntity.ok(responseFormat);
    }

}
