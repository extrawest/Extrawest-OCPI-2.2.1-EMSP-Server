package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.exception.OcpiGeneralClientException;
import com.extrawest.ocpi.exception.OcpiInvalidParametersException;
import com.extrawest.ocpi.model.ResponseFormat;
import com.extrawest.ocpi.model.dto.TariffDTO;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.EMSPTariffService;
import com.extrawest.ocpi.validation.ClientObjectValidation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emsp/api/2.2.1/tariffs")
@Tag(name = "EmspTariff")
@Validated
public class EMSPTariffController {

    protected final EMSPTariffService emspTariffService;

    public EMSPTariffController(@Autowired EMSPTariffService emspTariffService) {
        this.emspTariffService = emspTariffService;
    }

    /**
     * Retrieve a Tariff as it is stored in the eMSP’s system.
     *
     * @param countryCode Country code of the CPO performing the GET request on the eMSP’s system.
     * @param partyId     Party ID (Provider ID) of the CPO performing the GET request on the eMSP’s system.
     * @param tariffId    Party ID (Provider ID) of the CPO performing the GET request on the eMSP’s system.
     * @return The requested Tariff object.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<ResponseFormat<TariffDTO>> getTariff(
            @RequestParam(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @RequestParam(value = "party_id") @Size(min = 3, max = 3) String partyId,
            @RequestParam(value = "tariff_id") @Size(min = 1, max = 36) String tariffId) {
        TariffDTO tariffDto = emspTariffService.getTariff(countryCode, partyId, tariffId);

        ResponseFormat<TariffDTO> responseFormat = new ResponseFormat<TariffDTO>()
                .build(OcpiStatusCode.SUCCESS, tariffDto);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Push new/updated Tariff object to the eMSP.
     *
     * @param tariffDTO   New or updated Tariff object.
     * @param countryCode Country code of the CPO performing the PUT request on the eMSP’s system. This SHALL be
     *                    the same value as the country_code in the Tariff object being pushed.
     * @param partyId     Party ID (Provider ID) of the CPO performing the PUT request on the eMSP’s system. This SHALL be the same value as the party_id in the Tariff object being pushed.
     * @param tariffId    Tariff.id of the Tariff object to create or replace.
     * @throws OcpiGeneralClientException     if country_code and/or party_id in request parameters is other than
     *                                        country_code and/or party_id in request body
     * @throws OcpiInvalidParametersException if tariff_id in request parameters is other than in request body
     * @see <a href="https://github.com/ocpi/ocpi/blob/master/transport_and_format.asciidoc#errors">Errors</a>
     */
    @PutMapping
    public ResponseEntity<ResponseFormat<TariffDTO>> saveTariff(
            @RequestBody @Valid TariffDTO tariffDTO,
            @RequestParam(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @RequestParam(value = "party_id") @Size(min = 3, max = 3) String partyId,
            @RequestParam(value = "tariff_id") @Size(min = 1, max = 36) String tariffId) {
        ClientObjectValidation.checkClientCanModifyObject(tariffDTO, countryCode, partyId, tariffId);
        TariffDTO saved = emspTariffService.saveTariff(tariffDTO, countryCode, partyId, tariffId);

        ResponseFormat<TariffDTO> responseFormat = new ResponseFormat<TariffDTO>()
                .build(OcpiStatusCode.SUCCESS, saved);

        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Delete a Tariff object which is not used any more and will not be used in the future.
     *
     * @param countryCode Country code of the CPO performing the PUT request on the eMSP’s system.
     * @param partyId     Party ID (Provider ID) of the CPO performing the PUT request on the eMSP’s system.
     * @param tariffId    Tariff.id of the Tariff object to delete.
     */
    @DeleteMapping
    public ResponseEntity<ResponseFormat<TariffDTO>> deleteTariff(
            @RequestParam(value = "country_code") @Size(min = 2, max = 2) String countryCode,
            @RequestParam(value = "party_id") @Size(min = 3, max = 3) String partyId,
            @RequestParam(value = "tariff_id") @Size(min = 1, max = 36) String tariffId) {
        emspTariffService.deleteTariff(countryCode, partyId, tariffId);

        ResponseFormat<TariffDTO> responseFormat = new ResponseFormat<TariffDTO>()
                .build(OcpiStatusCode.SUCCESS);
        return ResponseEntity.ok(responseFormat);
    }

}
