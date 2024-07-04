package com.codeneeti.technexushub.exceptions;

import com.codeneeti.technexushub.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage>resourceNotFoundExceptionHandler(ResourceNotFoundException exception){
        ApiResponseMessage apiResponse = ApiResponseMessage.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .success(true)
                .build();
        return new ResponseEntity(apiResponse,HttpStatus.NOT_FOUND);
    }
    //methodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>>handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String,Object>response=new HashMap<>();
        allErrors.stream().forEach(objectError -> {
            String msg=objectError.getDefaultMessage();
            String field=((FieldError)objectError).getField();
            response.put(field,msg);
        });
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadApiRequestException.class)
    public ResponseEntity<ApiResponseMessage>badApiRequestExceptionHandler(BadApiRequestException exception){
        logger.info("bad request generated :");
        ApiResponseMessage apiResponse = ApiResponseMessage.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .success(false)
                .build();
        return new ResponseEntity(apiResponse,HttpStatus.NOT_FOUND);
    }

}
