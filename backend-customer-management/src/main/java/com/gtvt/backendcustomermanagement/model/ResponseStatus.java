package com.gtvt.backendcustomermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResponseStatus implements IResponseStatus, Serializable {
    private static final long serialVersionUID = 1L;

    public static String SUCCESS_CODE = "success";
    public static String JWT_TOKEN_IS_INVALID_CODE = "JWT_TOKEN_IS_INVALID";
    public static String SUCCESS_MESSAGE = "Success";
    public static String GENERAL_ERROR = "error";

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    public ResponseStatus() {
    }


    public ResponseStatus(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseStatus{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
