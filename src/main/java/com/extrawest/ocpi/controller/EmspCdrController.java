package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.cdr.CDR;
import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.EMSPCdrService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/emsp/api/2.2.1/cdr")
@Tag(name = "EmspCdr")
@Validated
public class EmspCdrController {

    protected final EMSPCdrService emspCdrService;

    public EmspCdrController(@Autowired EMSPCdrService emspCdrService) {
        this.emspCdrService = emspCdrService;
    }

    /**
     * Retrieve an existing CDR.
     *
     * @param id - id of CDR
     * @return The endpoint returns the requested CDR, if it exists.
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<CDR>> getCdr(
            @RequestParam(value = "id") @Size(min = 1, max = 36) String id) {
        CDR cdr = emspCdrService.getCdr(id);

        ResponseFormat<CDR> responseFormat = new ResponseFormat<CDR>()
                .build(OcpiStatusCode.SUCCESS, cdr);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Send a new CDR
     *
     * @param cdrDTO - CDR - Charge Detail Record
     * @return the URL where the newly created CDR can be found
     */
    @PostMapping
    public ResponseEntity<ResponseFormat<String>> postCdr(@RequestBody @Valid CDR cdrDTO) {
        String path = emspCdrService.postCdr(cdrDTO);

        ResponseFormat<String> responseFormat = new ResponseFormat<String>()
                .build(OcpiStatusCode.SUCCESS, path);
        return ResponseEntity.ok(responseFormat);
    }

}
