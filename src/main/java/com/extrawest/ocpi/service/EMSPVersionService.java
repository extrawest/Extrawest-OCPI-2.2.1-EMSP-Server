package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.VersionDetails;
import com.extrawest.ocpi.model.dto.Version;
import com.extrawest.ocpi.model.enums.VersionNumber;

import java.util.List;

public interface EMSPVersionService {

    List<Version> getVersions();

    VersionDetails getVersionDetails(VersionNumber version);

}
