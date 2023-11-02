package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.exception.OcpiInvalidParametersException;
import com.extrawest.ocpi.model.PaginationHeaders;
import com.extrawest.ocpi.model.ResponseFormat;
import com.extrawest.ocpi.model.dto.TokenDTO;
import com.extrawest.ocpi.model.dto.request.LocationReferences;
import com.extrawest.ocpi.model.dto.response.AuthorizationInfo;
import com.extrawest.ocpi.model.enums.TokenType;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.EMSPTokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@RestController
@RequestMapping("/emsp/api/2.2.1/tokens")
@Tag(name="EmspToken")
public class EMSPTokenController {

    protected final EMSPTokenService emspTokenService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Value("${maxXLimit}")
    private String maxXLimit;

    public EMSPTokenController(@Autowired EMSPTokenService emspTokenService) {
        this.emspTokenService = emspTokenService;
    }

    /**
     * Get the list of known Tokens, last updated between the {date_from} and {date_to} (paginated)
     * @param dateFrom Only return Tokens that have last_updated after or equal to this Date/Time (inclusive).
     * @param dateTo Only return Tokens that have last_updated up to this Date/Time, but not including (exclusive).
     * @param offset The offset of the first object returned. Default is 0.
     * @param limit Maximum number of objects to GET
     * @return List of all tokens.
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<TokenDTO>>> getTokens(
            @RequestParam(value = "date_from", required = false) LocalDateTime dateFrom,
            @RequestParam(value = "date_to", required = false) LocalDateTime dateTo,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", required = false) Integer limit,
            HttpServletRequest request) {

        limit = limit == null || limit > Integer.parseInt(maxXLimit) ? Integer.parseInt(maxXLimit) : limit;
        List<TokenDTO> tokens = emspTokenService.getToken(dateFrom, dateTo, offset, limit);
        long totalCount = emspTokenService.getTotalCount(dateFrom, dateTo);

        ResponseFormat<List<TokenDTO>> responseFormat = new ResponseFormat<List<TokenDTO>>()
                .build(OcpiStatusCode.SUCCESS, tokens);

        HttpHeaders responseHeaders = new HttpHeaders();
        if (hasNext(offset, limit, totalCount)){
            String uriForNextPage = constructNextPageUri(request, offset, limit);
            responseHeaders.set(PaginationHeaders.LINK, String.format("<%s>; rel=\"next\"", uriForNextPage));
        }

        responseHeaders.add(PaginationHeaders.X_LIMIT, maxXLimit);
        responseHeaders.add(PaginationHeaders.X_TOTAL_COUNT, String.valueOf(totalCount));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(responseFormat);
    }

    private boolean hasNext(int offset, int limit, long totalCount) {
        return offset + limit < totalCount;
    }

    private String constructNextPageUri(HttpServletRequest request, int offset, int limit) {
        return UriComponentsBuilder
                .fromUriString(request.getRequestURL().toString())
                .query(request.getQueryString())
                .replaceQueryParam("offset", offset + limit)
                .encode()
                .toUriString();
    }

    /**
     * Real-time authorization request
     * @param tokenUid Token.uid of the Token for which authorization is requested.
     * @param tokenType Token.type of the Token for which this authorization is. Default if omitted: RFID
     * @param locationReferences Location and EVSEs for which the Token is requested to be authorized.
     * @return Contains information about the authorization, if the Token is allowed to charge and optionally
     * which EVSEs are allowed to be used.
     */
    @PostMapping("/{token_uid}/authorize")
    public ResponseEntity<ResponseFormat<AuthorizationInfo>> postToken(
            @PathVariable(value = "token_uid") String tokenUid,
            @RequestParam(value = "type", required = false) String tokenType,
            @RequestBody @Valid LocationReferences locationReferences) {
        AuthorizationInfo dto = emspTokenService.postToken(tokenUid, tokenType, locationReferences);

        ResponseFormat<AuthorizationInfo> responseFormat = new ResponseFormat<AuthorizationInfo>()
                .build(OcpiStatusCode.SUCCESS, dto);
        return ResponseEntity.ok(responseFormat);
    }

}
