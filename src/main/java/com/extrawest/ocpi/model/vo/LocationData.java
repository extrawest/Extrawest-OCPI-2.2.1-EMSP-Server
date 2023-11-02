package com.extrawest.ocpi.model.vo;

import com.extrawest.ocpi.model.OcpiRequestData;
import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.dto.LocationDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/*
* An interface to mark a location data. Can be Location, EVSE or Connector
 */
public interface LocationData extends OcpiRequestData, OcpiResponseData{

}
