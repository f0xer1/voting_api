package com.votingapi.foxer.web;

import com.votingapi.foxer.exeption.UserNotFoundException;
import com.votingapi.foxer.web.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class VotingApiControllerAdvice {
    @ExceptionHandler({
            UserNotFoundException.class,
            RoleNotFoundException.class,

    })
    public ResponseEntity<ExceptionResponse> handleNotFound(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse(exception.getMessage()));
    }

    private ExceptionResponse exceptionResponse(String message) {
        return new ExceptionResponse(message,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    }
}
