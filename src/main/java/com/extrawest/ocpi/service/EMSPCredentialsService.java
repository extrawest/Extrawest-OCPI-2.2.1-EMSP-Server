package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.CredentialsDto;
import com.extrawest.ocpi.model.enums.VersionNumber;

public interface EMSPCredentialsService {

    CredentialsDto getCredentials(String authToken);

    CredentialsDto postCredentials(CredentialsDto credentialsDTO, String authToken, VersionNumber versionNumber);

    void putCredentials(CredentialsDto credentialsDTO);

    void deleteCredentials(String authToken);

}
