package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.ClientInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface HubClientInfoService {

    List<ClientInfo> getClientInfoList(LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);

}
