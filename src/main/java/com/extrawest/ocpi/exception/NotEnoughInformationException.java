package com.extrawest.ocpi.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public class NotEnoughInformationException extends RuntimeException {

    public NotEnoughInformationException(String...reasons) {
        super(getErrorLogMsg(reasons));
    }

    private static String getErrorLogMsg(String...reasons) {
        return reasons.length == 0 ? Strings.EMPTY : Arrays.toString(reasons);
    }
}