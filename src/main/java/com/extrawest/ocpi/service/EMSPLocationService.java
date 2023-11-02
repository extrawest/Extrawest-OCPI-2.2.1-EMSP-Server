package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.LocationDTO;
import com.extrawest.ocpi.model.vo.Connector;
import com.extrawest.ocpi.model.vo.Evse;
import com.extrawest.ocpi.model.vo.LocationData;

public interface EMSPLocationService {
    LocationDTO getLocation(String countryCode, String partyId, String locationId);

    Evse getEvse(String countryCode, String partyId, String locationId, String evseUid);

    Connector getConnector(String countryCode, String partyId, String locationId, String evseUid,
                           String connectorId);

    LocationDTO pushLocation(LocationDTO locationDTO,
                      String countryCode,
                      String partyId,
                      String locationId);


    Evse pushEvse(Evse locationDTO,
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

    LocationData patchLocation(LocationDTO locationDTO,
                       String countryCode,
                       String partyId,
                       String locationId,
                       String evseUid,
                       String connectorId);

}
