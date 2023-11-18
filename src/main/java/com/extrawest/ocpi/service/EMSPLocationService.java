package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.location.Connector;
import com.extrawest.ocpi.model.dto.location.EVSE;
import com.extrawest.ocpi.model.dto.location.Location;
import com.extrawest.ocpi.model.markers.LocationData;

public interface EMSPLocationService {
    Location getLocation(String countryCode, String partyId, String locationId);

    EVSE getEvse(String countryCode, String partyId, String locationId, String evseUid);

    Connector getConnector(String countryCode, String partyId, String locationId, String evseUid,
                           String connectorId);

    Location pushLocation(Location locationDTO,
                          String countryCode,
                          String partyId,
                          String locationId);


    EVSE pushEvse(EVSE locationDTO,
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

    LocationData patchLocation(Location locationDTO,
                               String countryCode,
                               String partyId,
                               String locationId,
                               String evseUid,
                               String connectorId);

}
