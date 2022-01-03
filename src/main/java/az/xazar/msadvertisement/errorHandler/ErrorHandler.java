package az.xazar.msadvertisement.errorHandler;

import az.xazar.msadvertisement.model.Ad.exception.AdErrorCodes;
import az.xazar.msadvertisement.model.Ad.exception.AdNotFoundException;
import az.xazar.msadvertisement.model.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//import static az.xazar.msinquiry.model.Ad.exception.AdErrorCodes.NOT_FOUND;


@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ErrorHandler.class.getName());

    @ExceptionHandler(AdNotFoundException.class)
    public ResponseEntity<Object> handleAdNotFoundException(AdNotFoundException ex,
                                                            WebRequest webRequest) {
        logger.info(ex.toString());

        return handleExceptionInternal(ex, ErrorDto.builder()
                        .code(AdErrorCodes.NOT_FOUND)
                        .message(ex.getMessage())
                        .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex,
                                                     WebRequest webRequest) {
        logger.info(ex.getMessage());

        return handleExceptionInternal(ex, ErrorDto.builder()
                        .code(AdErrorCodes.UNEXPECTED_EXCEPTION)
                        .message(ex.getMessage())
                        .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}
