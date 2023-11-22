package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.SessionDto;

public interface EMSPSessionsService {

    SessionDto getSession(String countryCode, String partyId, String sessionId);

    SessionDto putSession(SessionDto sessionDTO, String countryCode, String partyId, String sessionId);

    SessionDto patchSession(SessionDto sessionDTO, String countryCode, String partyId, String sessionId);

}
