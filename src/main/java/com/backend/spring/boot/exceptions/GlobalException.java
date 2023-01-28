package com.backend.spring.boot.exceptions;

import com.backend.spring.boot.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundHandler(ResourceNotFound ex){
        String message=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);
        return  new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){

        Map<String,String> mp=new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(e->{
            String fieldName= ((FieldError) e).getField();
            String mesage=e.getDefaultMessage();

            mp.put(fieldName,mesage);
        });

        String msg="Inavlid Input";

        ApiResponse apiResponse=new ApiResponse(msg,false,mp);


        return  new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }
}
