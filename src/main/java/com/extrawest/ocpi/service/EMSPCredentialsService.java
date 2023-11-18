package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.Credentials;

public interface EMSPCredentialsService {

    Credentials getCredentials();

    Credentials postCredentials(Credentials credentialsToClientDto);

    void putCredentials(Credentials credentialsDTO);

    void deleteCredentials(Credentials credentialsDTO);

}
