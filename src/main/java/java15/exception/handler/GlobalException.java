package java15.exception.handler;

import java15.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java15.exception.BadRequestException;
import java15.exception.response.ExceptionResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    public ExceptionResponse notFound(NotFoundException notfoundException) {
        return ExceptionResponse.
                builder()
                .status(HttpStatus.NOT_FOUND)
                .exceptionsClassName(NotFoundException.class.getName())
                .message(notfoundException.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 404
    public ExceptionResponse badRequest(BadRequestException e) {
        return ExceptionResponse.
                builder()
                .status(HttpStatus.BAD_REQUEST)
                .exceptionsClassName(BadRequestException.class.getName())
                .message(e.getMessage())
                .build();

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse methodArgNotValidType(MethodArgumentTypeMismatchException methodArgumentNotValidTypeException) {
        return ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .exceptionsClassName(methodArgumentNotValidTypeException.getClass().getSimpleName()).
                message(methodArgumentNotValidTypeException.getMessage())
                .build();
    }

    //    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> methodArgNotValid(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}








