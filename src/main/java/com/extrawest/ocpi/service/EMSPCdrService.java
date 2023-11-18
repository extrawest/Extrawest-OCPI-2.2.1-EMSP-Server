package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.cdr.CDR;

public interface EMSPCdrService {

    CDR getCdr(String id);

    String postCdr(CDR cdrDTO);

}
