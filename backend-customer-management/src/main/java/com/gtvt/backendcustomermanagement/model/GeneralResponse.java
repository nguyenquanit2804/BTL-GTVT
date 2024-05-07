package com.gtvt.backendcustomermanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("status")
    private ResponseStatus status;

    @JsonProperty("data")
    private T data;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GeneralResponse{");
        sb.append("status=").append(status);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
