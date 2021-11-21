package com.bakulovas.tta.errors;

import com.bakulovas.tta.dto.response.ErrorsDtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//todo, not ready
@ControllerAdvice
class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorsDtoResponse handleException(Exception ex) {
        ErrorsDtoResponse errors = new ErrorsDtoResponse();
        errors.addError(new Error(ex.getMessage()));
        return errors;
    }

}
