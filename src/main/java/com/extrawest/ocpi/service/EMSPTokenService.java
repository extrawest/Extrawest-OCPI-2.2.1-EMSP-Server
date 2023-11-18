package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.AuthorizationInfo;
import com.extrawest.ocpi.model.dto.LocationReferences;
import com.extrawest.ocpi.model.dto.token.Token;

import java.time.LocalDateTime;
import java.util.List;

public interface EMSPTokenService {

    List<Token> getToken(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

    long getTotalCount(LocalDateTime dateFrom, LocalDateTime dateTo);

    AuthorizationInfo postToken(String tokenUid,
                                String type,
                                LocationReferences locationReferences);

}
