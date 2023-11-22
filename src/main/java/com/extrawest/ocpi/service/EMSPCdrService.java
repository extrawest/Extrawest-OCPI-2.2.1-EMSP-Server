package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.cdr.CDRDto;

public interface EMSPCdrService {

    CDRDto getCdr(String id);

    String postCdr(CDRDto cdrDTO);

}
