package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.command.CommandResult;
import com.extrawest.ocpi.model.enums.CommandType;

public interface EMSPCommandsService {

    void postCommand(CommandResult commandResultRequestDTO, CommandType commandType, String uniqueId);

}
