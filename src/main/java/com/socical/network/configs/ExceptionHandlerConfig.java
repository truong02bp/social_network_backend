package com.socical.network.configs;

import com.socical.network.data.dto.ErrorMessage;
import com.socical.network.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ErrorMessage> apiException(BusinessException e) {
        e.printStackTrace();
        return ResponseEntity.ok(ErrorMessage.fromApiException(e));
    }

}
