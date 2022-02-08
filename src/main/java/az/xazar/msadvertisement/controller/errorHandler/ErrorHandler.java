package az.xazar.msadvertisement.controller.errorHandler;

import az.xazar.msadvertisement.model.ErrorDto;
import az.xazar.msadvertisement.model.exception.AdNotFoundException;
import az.xazar.msadvertisement.model.exception.ResultTypeException;
import az.xazar.msadvertisement.model.exception.WrongEditIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static az.xazar.msadvertisement.model.exception.ErrorCodes.*;


@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ErrorHandler.class.getName());

    @ExceptionHandler(AdNotFoundException.class)
    public ResponseEntity<Object> handleAdNotFoundException(AdNotFoundException ex,
                                                            WebRequest webRequest) {
        logger.info(ex.toString());

        return handleExceptionInternal(ex, ErrorDto.builder()
                        .code(NOT_FOUND)
                        .message(ex.getMessage())
                        .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(ResultTypeException.class)
    public ResponseEntity<Object> handleResultTypeException(ResultTypeException ex,
                                                            WebRequest webRequest) {
        logger.info(ex.toString());

        return handleExceptionInternal(ex, ErrorDto.builder()
                        .code(RESULT_TYPE_EXCEPTION)
                        .message(ex.getMessage())
                        .build(),
                new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(WrongEditIdException.class)
    public ResponseEntity<Object> handleWrongEditIdException(WrongEditIdException ex,
                                                             WebRequest webRequest) {
        logger.info(ex.toString());

        return handleExceptionInternal(ex, ErrorDto.builder()
                        .code(WRONG_EDIT_ID_EXCEPTION)
                        .message(ex.getMessage())
                        .build(),
                new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex,
                                                     WebRequest webRequest) {
        logger.info(ex.getMessage());

        return handleExceptionInternal(ex, ErrorDto.builder()
                        .code(UNEXPECTED_EXCEPTION)
                        .message(ex.getMessage())
                        .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}
