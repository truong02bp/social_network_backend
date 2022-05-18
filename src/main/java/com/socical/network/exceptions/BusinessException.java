package com.socical.network.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    protected HttpStatus status;
    protected String message;

}
