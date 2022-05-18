package com.socical.network.data.dto;

import com.socical.network.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;

    private String message;

    public ErrorMessage() {
    }

    public static ErrorMessage fromApiException(BusinessException exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.message = exception.getMessage();
        errorMessage.statusCode = exception.getStatus().value();
        return errorMessage;
    }

}
