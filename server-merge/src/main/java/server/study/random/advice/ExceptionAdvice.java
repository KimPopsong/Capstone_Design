package server.study.random.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.study.random.advice.exception.AuthenticationEntryPointException;
import server.study.random.advice.exception.MemberException;
import server.study.random.model.common.ExceptionResponse;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({MemberException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse exceptionHandling(Exception e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return exceptionResponse;
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse exceptionResponse(AuthenticationEntryPointException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return exceptionResponse;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse exceptionResponse(AccessDeniedException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return exceptionResponse;
    }
}
