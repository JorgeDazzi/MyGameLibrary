package br.dazzi.gamelibrary.controller.response.exceptionhandler;


import br.dazzi.gamelibrary.controller.response.error.ErrorMessage;
import br.dazzi.gamelibrary.controller.response.error.ErrorResponse;
import br.dazzi.gamelibrary.domain.exception.APIException;
import br.dazzi.gamelibrary.domain.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class DefaultExceptionHandler {

    Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    //This going to handle any Exception which was not predicted
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleError(Exception exception, HttpServletRequest request){
        logger.error("Unknown error", exception);
        return getDefaultResponseMessage(request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException notFoundException, HttpServletRequest request) {
        return buildErrorResponse(notFoundException, HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(APIException exception, HttpStatus httpStatus, HttpServletRequest request) {
        try {
            List<ErrorMessage> errorMessages = List.of(new ErrorMessage(exception.getCode(), exception.getDescription(), exception.getMessage()));
            return new ResponseEntity<>(new ErrorResponse(errorMessages), httpStatus);
        } catch (Exception e) {
            return getDefaultResponseMessage(request);
        }
    }

    private ResponseEntity<ErrorResponse> getDefaultResponseMessage(HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(500, "internal_error", "Internal Error");
        return new ResponseEntity<>(new ErrorResponse(List.of(errorMessage)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
