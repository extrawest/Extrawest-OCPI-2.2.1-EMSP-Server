package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.charging_profile.ActiveChargingProfile;
import com.extrawest.ocpi.model.dto.charging_profile.results.AbstractProfileResult;
import com.extrawest.ocpi.model.dto.ResponseFormat;

public interface EMSPChargingProfilesService {

    ResponseFormat postChargingProfile(AbstractProfileResult abstractProfileResult);

    ResponseFormat putChargingProfile(String sessionId, ActiveChargingProfile activeChargingProfile);

}
