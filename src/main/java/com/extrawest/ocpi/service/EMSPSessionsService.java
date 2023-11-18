package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.Session;

public interface EMSPSessionsService {

    Session getSession(String countryCode, String partyId, String sessionId);

    Session putSession(Session sessionDTO, String countryCode, String partyId, String sessionId);

    void patchSession(Session sessionDTO, String countryCode, String partyId, String sessionId);

}
