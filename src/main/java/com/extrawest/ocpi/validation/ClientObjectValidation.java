package com.extrawest.ocpi.validation;

import com.extrawest.ocpi.exception.OcpiGeneralClientException;
import com.extrawest.ocpi.exception.OcpiInvalidParametersException;
import com.extrawest.ocpi.model.dto.ClientOwnedObject;
import com.extrawest.ocpi.model.vo.Connector;
import com.extrawest.ocpi.model.vo.Evse;
import com.extrawest.ocpi.model.vo.LocationData;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class ClientObjectValidation {
    /**
     * @throws OcpiGeneralClientException if country_code and/or party_id in request parameters is other than
     * country_code and/or party_id in request body
     * @throws OcpiInvalidParametersException if tariff_id in request parameters is other than in request body
     * @see <a href="https://github.com/ocpi/ocpi/blob/master/transport_and_format.asciidoc#errors">Errors</a>
     */

    public static <T extends ClientOwnedObject> void checkClientCanModifyObject(T object,
                                                                                String countryCode,
                                                                                String partyId,
                                                                                String id) {
        if (!Objects.equals(object.getCountryCode(), countryCode)
                || !Objects.equals(object.getPartyId(), partyId))
            throw new OcpiGeneralClientException();

        if (!Objects.equals(object.getId(), id)) {
            throw new OcpiInvalidParametersException();
        }
    }

    public static void checkClientCanModifyObject(Evse object,
                                                  String id) {
        if (!Objects.equals(object.getUid(), id)) {
            throw new OcpiInvalidParametersException();
        }
    }

    public static void checkClientCanModifyObject(Connector object,
                                                  String id) {
        if (!Objects.equals(object.getConnectorId(), id)) {
            throw new OcpiInvalidParametersException();
        }
    }

}
