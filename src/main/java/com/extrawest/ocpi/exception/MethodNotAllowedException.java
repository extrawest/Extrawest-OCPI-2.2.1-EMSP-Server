package com.extrawest.ocpi.exception;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public class MethodNotAllowedException extends RuntimeException {
    public MethodNotAllowedException(String...reasons) {
        super(getErrorLogMsg(reasons));
    }

    private static String getErrorLogMsg(String...reasons) {
        return reasons.length == 0 ? Strings.EMPTY : Arrays.toString(reasons);
    }
}