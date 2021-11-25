package com.bakulovas.tta.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorsDtoResponse {

    private final List<Error> errors;

    public ErrorsDtoResponse() {
        this.errors = new ArrayList<>();
    }

    public void addError(Error error) {
        errors.add(error);
    }


}
