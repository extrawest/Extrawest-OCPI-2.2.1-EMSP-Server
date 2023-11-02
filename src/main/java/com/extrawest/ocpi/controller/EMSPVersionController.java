package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.ResponseFormat;
import com.extrawest.ocpi.model.dto.TariffDTO;
import com.extrawest.ocpi.model.dto.response.VersionDetailsResponseDTO;
import com.extrawest.ocpi.model.dto.response.VersionResponseDTO;
import com.extrawest.ocpi.model.enums.VersionNumber;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.EMSPVersionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/emsp/api/versions")
@Tag(name="EmspVersion")
public class EMSPVersionController {

    protected final EMSPVersionService emspVersionService;

    public EMSPVersionController(@Autowired EMSPVersionService emspVersionService) {
        this.emspVersionService = emspVersionService;
    }

    /**
     * Fetch information about the supported versions.
     * @return list of VersionResponseDTO
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<VersionResponseDTO>>> getVersions() {
        List<VersionResponseDTO> versions = emspVersionService.getVersions();

        ResponseFormat<List<VersionResponseDTO>> responseFormat = new ResponseFormat<List<VersionResponseDTO>>()
                .build(OcpiStatusCode.SUCCESS, versions);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Via the version details, the parties can exchange which modules are implemented for a specific version of OCPI,
     * which interface role is implemented, and what the endpoint URL is for this interface.
     * @param version - version of OCPI
     * @return VersionDetails
     */
    @GetMapping("/details")
    public ResponseEntity<ResponseFormat<VersionDetailsResponseDTO>> getVersionDetails(
            @RequestParam(value = "version") String version
    ) {
        VersionDetailsResponseDTO versionDetails = emspVersionService.getVersionDetails(VersionNumber.fromValue(version));

        ResponseFormat<VersionDetailsResponseDTO> responseFormat = new ResponseFormat<VersionDetailsResponseDTO>()
                .build(OcpiStatusCode.SUCCESS, versionDetails);
        return ResponseEntity.ok(responseFormat);
    }
}
