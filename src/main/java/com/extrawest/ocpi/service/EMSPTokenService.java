package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.AuthorizationInfoDto;
import com.extrawest.ocpi.model.dto.LocationReferencesDto;
import com.extrawest.ocpi.model.dto.token.TokenDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EMSPTokenService {

    List<TokenDto> getToken(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

    long getTotalCount(LocalDateTime dateFrom, LocalDateTime dateTo);

    AuthorizationInfoDto postToken(String tokenUid,
                                   String type,
                                   LocationReferencesDto locationReferences);

}
