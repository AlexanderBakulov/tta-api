package com.bakulovas.tta.errors;

import com.bakulovas.tta.dto.response.ErrorsDtoResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

//todo, not ready
@ControllerAdvice
class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServerException.class)
    @ResponseBody
    public ErrorsDtoResponse handleException(ServerException ex) {
        ErrorsDtoResponse errors = new ErrorsDtoResponse();
        errors.addError(new Error(ex.getServerErrorString()));
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public ErrorsDtoResponse handleDataAccess(DataAccessException ex) {
        ErrorsDtoResponse errors = new ErrorsDtoResponse();
        errors.addError(new Error(ex.getMessage()));
        return errors;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorsDtoResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorsDtoResponse errors = new ErrorsDtoResponse();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.addError(new Error(errorMessage));
        });
        return errors;
    }



    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ErrorsDtoResponse handleNPE(NullPointerException ex) {
        ErrorsDtoResponse errors = new ErrorsDtoResponse();
        errors.addError(new Error(ex.getMessage()));
        return errors;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorsDtoResponse handleException(Exception ex) {
        ErrorsDtoResponse errors = new ErrorsDtoResponse();
        errors.addError(new Error(ex.getMessage()));
        return errors;
    }



}
