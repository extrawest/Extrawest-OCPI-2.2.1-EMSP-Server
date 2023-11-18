package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.ClientInfo;

public interface ClientInfoService {

    ClientInfo getHubClientInfo(String countryCode, String partyId);

    void putHubClientInfo(String countryCode, String partyId);

}
