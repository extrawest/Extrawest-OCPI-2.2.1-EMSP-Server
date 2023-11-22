package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.tariff.TariffDto;

public interface EMSPTariffService {
    TariffDto getTariff(String countryCode, String partyId, String tariffId);

    TariffDto saveTariff(TariffDto tariffDTO, String countryCode, String partyId, String tariffId);

    void deleteTariff(String countryCode, String partyId, String tariffId);
}
