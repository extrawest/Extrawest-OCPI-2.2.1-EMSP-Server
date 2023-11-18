package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.tariff.Tariff;

public interface EMSPTariffService {
    Tariff getTariff(String countryCode, String partyId, String tariffId);

    Tariff saveTariff(Tariff tariffDTO, String countryCode, String partyId, String tariffId);

    void deleteTariff(String countryCode, String partyId, String tariffId);
}
