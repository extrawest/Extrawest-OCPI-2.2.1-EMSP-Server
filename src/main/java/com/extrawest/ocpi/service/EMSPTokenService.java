package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.TokenDTO;
import com.extrawest.ocpi.model.dto.request.LocationReferences;
import com.extrawest.ocpi.model.dto.response.AuthorizationInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface EMSPTokenService {

    List<TokenDTO> getToken(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

    long getTotalCount(LocalDateTime dateFrom, LocalDateTime dateTo);

    AuthorizationInfo postToken(String tokenUid,
                                String type,
                                LocationReferences locationReferences);

}
