package com.extrawest.ocpi.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessage {
    public static final String PARTY_AND_COUNTRY_DIFFERS = "Party_id and country_code need to be the same in body and in url";
    public static final String OBJECT_IDS_IS_DIFFERENT = "%s id need to be the same in body and in url";
}
