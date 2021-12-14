package com.bakulovas.tta.errors;

public class ServerException extends RuntimeException {

    private final ServerError serverError;
    private final String field;
    private final String message;

    public ServerException(ServerError serverError, String field, String message) {
        this.serverError = serverError;
        this.field = field;
        this.message = message;
    }

    public ServerException(ServerError serverError, String field) {
        this(serverError, field, serverError.getErrorString());
    }

    public ServerException(ServerError serverError) {
        this(serverError, null, serverError.getErrorString());
    }


    public ServerError getServerCode() {
        return serverError;
    }

    public String getServerErrorString() {
        return serverError.getErrorString();
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }





}
