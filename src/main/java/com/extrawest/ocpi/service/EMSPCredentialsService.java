package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.CredentialsDto;

public interface EMSPCredentialsService {

    CredentialsDto getCredentials();

    CredentialsDto postCredentials(CredentialsDto credentialsToClientDto);

    void putCredentials(CredentialsDto credentialsDTO);

    void deleteCredentials(CredentialsDto credentialsDTO);

}
