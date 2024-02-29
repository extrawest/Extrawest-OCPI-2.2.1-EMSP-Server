package com.extrawest.ocpi.util;

import com.extrawest.ocpi.exception.OcpiUnknownTokenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenAuthenticationService {
    private final static String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Token ";

    public static String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader == null
                || !authHeader.startsWith(TOKEN_PREFIX)
                || authHeader.substring(TOKEN_PREFIX.length()).isEmpty()) {
            throw new OcpiUnknownTokenException();
        }

        return authHeader;
    }

}
