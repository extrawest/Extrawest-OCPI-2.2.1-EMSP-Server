package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.VersionDetails;
import com.extrawest.ocpi.model.dto.Version;
import com.extrawest.ocpi.model.enums.VersionNumber;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.EMSPVersionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emsp/api/versions")
@Tag(name = "EmspVersion")
public class EmspVersionController {

    protected final EMSPVersionService emspVersionService;

    public EmspVersionController(@Autowired EMSPVersionService emspVersionService) {
        this.emspVersionService = emspVersionService;
    }

    /**
     * Fetch information about the supported versions.
     *
     * @return list of VersionResponseDTO
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<Version>>> getVersions() {
        List<Version> versions = emspVersionService.getVersions();

        ResponseFormat<List<Version>> responseFormat = new ResponseFormat<List<Version>>()
                .build(OcpiStatusCode.SUCCESS, versions);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Via the version details, the parties can exchange which modules are implemented for a specific version of OCPI,
     * which interface role is implemented, and what the endpoint URL is for this interface.
     *
     * @param version - version of OCPI
     * @return VersionDetails
     */
    @GetMapping("/details")
    public ResponseEntity<ResponseFormat<VersionDetails>> getVersionDetails(
            @RequestParam(value = "version") String version
    ) {
        VersionDetails versionDetails = emspVersionService.getVersionDetails(VersionNumber.fromValue(version));

        ResponseFormat<VersionDetails> responseFormat = new ResponseFormat<VersionDetails>()
                .build(OcpiStatusCode.SUCCESS, versionDetails);
        return ResponseEntity.ok(responseFormat);
    }
}
