package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.TariffDTO;

public interface EMSPTariffService {
    TariffDTO getTariff(String countryCode, String partyId, String tariffId);
    TariffDTO saveTariff(TariffDTO tariffDTO, String countryCode, String partyId, String tariffId);
    void deleteTariff(String countryCode, String partyId, String tariffId);
}
