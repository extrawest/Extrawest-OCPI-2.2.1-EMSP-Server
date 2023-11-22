package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.location.Connector;
import com.extrawest.ocpi.model.dto.location.EVSE;
import com.extrawest.ocpi.model.dto.location.Location;

public interface EMSPLocationService {
    Location getLocation(String countryCode, String partyId, String locationId);

    EVSE getEvse(String countryCode, String partyId, String locationId, String evseUid);

    Connector getConnector(String countryCode, String partyId, String locationId, String evseUid,
                           String connectorId);

    Location pushLocation(Location locationDto,
                          String countryCode,
                          String partyId,
                          String locationId);


    EVSE pushEvse(EVSE locationDto,
                  String countryCode,
                  String partyId,
                  String locationId,
                  String evseUid);

    Connector pushConnector(Connector connector,
                            String countryCode,
                            String partyId,
                            String locationId,
                            String evseUid,
                            String connectorId);

    Location patchLocation(Location locationDTO, String countryCode, String partyId, String locationId);

    EVSE patchEvse(EVSE evse, String countryCode, String partyId, String locationId, String evseUid);

    Connector patchConnector(Connector connector, String countryCode, String partyId, String locationId,
                             String evseUid, String connectorId);
}

