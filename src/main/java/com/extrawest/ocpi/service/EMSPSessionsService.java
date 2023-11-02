package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.SessionDTO;

public interface EMSPSessionsService {

    SessionDTO getSession(String countryCode, String partyId, String sessionId);
    SessionDTO putSession(SessionDTO sessionDTO, String countryCode, String partyId, String sessionId);
    void patchSession(SessionDTO sessionDTO, String countryCode, String partyId, String sessionId);

}
