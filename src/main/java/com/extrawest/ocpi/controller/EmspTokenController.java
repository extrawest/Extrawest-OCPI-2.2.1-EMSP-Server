package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.AuthorizationInfoDto;
import com.extrawest.ocpi.model.dto.LocationReferencesDto;
import com.extrawest.ocpi.model.dto.PaginationHeaders;
import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.token.TokenDto;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.EMSPTokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/emsp/api/2.2.1/tokens")
@Tag(name = "EmspToken")
@Validated
public class EmspTokenController {

    protected final EMSPTokenService emspTokenService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Value("${maxXLimit}")
    private String maxXLimit;

    public EmspTokenController(@Autowired EMSPTokenService emspTokenService) {
        this.emspTokenService = emspTokenService;
    }

    /**
     * Get the list of known Tokens, last updated between the {date_from} and {date_to} (paginated)
     *
     * @param dateFrom Only return Tokens that have last_updated after or equal to this Date/Time (inclusive).
     * @param dateTo   Only return Tokens that have last_updated up to this Date/Time, but not including (exclusive).
     * @param offset   The offset of the first object returned. Default is 0.
     * @param limit    Maximum number of objects to GET
     * @return List of all tokens.
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<TokenDto>>> getTokens(
            @RequestParam(value = "date_from", required = false) LocalDateTime dateFrom,
            @RequestParam(value = "date_to", required = false) LocalDateTime dateTo,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", required = false) Integer limit,
            HttpServletRequest request) {

        limit = limit == null || limit > Integer.parseInt(maxXLimit) ? Integer.parseInt(maxXLimit) : limit;
        List<TokenDto> tokens = emspTokenService.getToken(dateFrom, dateTo, offset, limit);
        long totalCount = emspTokenService.getTotalCount(dateFrom, dateTo);

        ResponseFormat<List<TokenDto>> responseFormat = new ResponseFormat<List<TokenDto>>()
                .build(OcpiStatusCode.SUCCESS, tokens);

        HttpHeaders responseHeaders = new HttpHeaders();
        if (hasNext(offset, limit, totalCount)) {
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
     *
     * @param tokenUid           Token.uid of the Token for which authorization is requested.
     * @param tokenType          Token.type of the Token for which this authorization is. Default if omitted: RFID
     * @param locationReferences Location and EVSEs for which the Token is requested to be authorized.
     * @return Contains information about the authorization, if the Token is allowed to charge and optionally
     * which EVSEs are allowed to be used.
     */
    @PostMapping("/{token_uid}/authorize")
    public ResponseEntity<ResponseFormat<AuthorizationInfoDto>> postToken(
            @PathVariable(value = "token_uid") @Size(min = 1, max = 36) String tokenUid,
            @RequestParam(value = "type", required = false) String tokenType,
            @RequestBody @Valid LocationReferencesDto locationReferences) {
        AuthorizationInfoDto dto = emspTokenService.postToken(tokenUid, tokenType, locationReferences);

        ResponseFormat<AuthorizationInfoDto> responseFormat = new ResponseFormat<AuthorizationInfoDto>()
                .build(OcpiStatusCode.SUCCESS, dto);
        return ResponseEntity.ok(responseFormat);
    }

}
