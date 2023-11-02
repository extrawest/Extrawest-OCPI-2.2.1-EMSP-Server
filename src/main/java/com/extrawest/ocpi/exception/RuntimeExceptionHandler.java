package com.extrawest.ocpi.exception;

import com.extrawest.ocpi.model.OcpiResponseData;
import com.extrawest.ocpi.model.ResponseFormat;
import com.extrawest.ocpi.model.dto.response.VersionResponseDTO;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RuntimeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {OcpiInvalidParametersException.class,
            OcpiGeneralClientException.class,
            OcpiResourceNotFoundException.class})
    public ResponseEntity<ResponseFormat<OcpiResponseData>> handleException(RuntimeException ex) {
        HttpStatus httpStatus;
        OcpiStatusCode ocpiStatusCode;

        if (ex instanceof OcpiInvalidParametersException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            ocpiStatusCode = OcpiStatusCode.INVALID_PARAMETERS;
        }
        else if (ex instanceof OcpiGeneralClientException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            ocpiStatusCode = OcpiStatusCode.CLIENT_ERROR;
        }
        else if (ex instanceof OcpiResourceNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
            ocpiStatusCode = OcpiStatusCode.CLIENT_ERROR;
        }
        else if (ex instanceof OcpiUnknownTokenException) {
            httpStatus = HttpStatus.NOT_FOUND;
            ocpiStatusCode = OcpiStatusCode.UNKNOWN_TOKEN;
        }
        else if (ex instanceof NotEnoughInformationException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            ocpiStatusCode = OcpiStatusCode.NOT_ENOUGH_INFORMATION;
        }
        else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            ocpiStatusCode = OcpiStatusCode.SERVER_ERROR;
        }
        return handleExceptionInternal(ex, httpStatus, ocpiStatusCode);
    }

    private ResponseEntity<ResponseFormat<OcpiResponseData>> handleExceptionInternal(RuntimeException ex,
                                                                                     HttpStatus status,
                                                                                     OcpiStatusCode ocpiStatusCode) {

        ResponseFormat<OcpiResponseData> responseFormat = new ResponseFormat<OcpiResponseData>()
                .build(ocpiStatusCode);

        responseFormat.setStatusMessage(String.format("%s. %s",
                responseFormat.getStatusMessage(), ex.getMessage()));

        log.error("Request failed: {} ({})", ocpiStatusCode.getValue(), ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(responseFormat, status);
    }

}
