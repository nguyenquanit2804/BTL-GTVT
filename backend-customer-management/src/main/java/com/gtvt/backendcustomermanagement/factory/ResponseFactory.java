package com.gtvt.backendcustomermanagement.factory;


import com.gtvt.backendcustomermanagement.model.GeneralResponse;
import com.gtvt.backendcustomermanagement.model.IResponseStatus;
import com.gtvt.backendcustomermanagement.model.ManagementResponseStatus;
import com.gtvt.backendcustomermanagement.model.ResponseStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {


    public ResponseEntity<?> success() {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ResponseStatus.SUCCESS_CODE);
        responseStatus.setMessage(ResponseStatus.SUCCESS_MESSAGE);
        responseObject.setStatus(responseStatus);
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity<?> success(Object data, Class<?> clazz) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ResponseStatus.SUCCESS_CODE);
        responseStatus.setMessage(ResponseStatus.SUCCESS_MESSAGE);
        responseObject.setStatus(responseStatus);
        responseObject.setData(clazz.cast(data));
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity success(Object data) {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ManagementResponseStatus.SUCCESS.getCode());
        responseStatus.setMessage(ManagementResponseStatus.SUCCESS.getMessage());
        responseObject.setStatus(responseStatus);
        responseObject.setData(data);
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode, String errorMessage) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseStatus.setCode(errorCode);
        responseStatus.setMessage(errorMessage);
        responseObject.setStatus(responseStatus);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(responseObject, httpStatus);
        return responseEntity;
    }

    public ResponseEntity error(HttpStatus httpStatus, ResponseStatus responseStatus) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseObject.setStatus(responseStatus);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(responseObject, httpStatus);
        return responseEntity;
    }

    public ResponseEntity error(HttpStatus httpStatus, ManagementResponseStatus selfCareResponseStatus) {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(selfCareResponseStatus.getCode());
        responseStatus.setMessage(selfCareResponseStatus.getMessage());
        GeneralResponse<Object> response = new GeneralResponse<>();
        response.setStatus(responseStatus);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(response, httpStatus);
        return responseEntity;
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode, String errorMessage, Object data, Class<?> clazz) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseStatus.setCode(errorCode);
        responseStatus.setMessage(errorMessage);
        responseObject.setStatus(responseStatus);
        responseObject.setData(clazz.cast(data));
        ResponseEntity<?> responseEntity = new ResponseEntity<>(responseObject, httpStatus);
        return responseEntity;
    }

    public ResponseEntity error(HttpStatus httpStatus, ManagementResponseStatus status, String field) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseStatus.setCode(status.getCode());
        responseStatus.setMessage(String.format(status.getMessage(), field));
        responseObject.setStatus(responseStatus);
        ResponseEntity<?> responseEntity = new ResponseEntity(responseObject, httpStatus);
        return responseEntity;
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode, String errorMessage, Object data) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseStatus.setCode(errorCode);
        responseStatus.setMessage(errorMessage);
        responseObject.setStatus(responseStatus);
        responseObject.setData(data);
        ResponseEntity<?> responseEntity = new ResponseEntity(responseObject, httpStatus);
        return responseEntity;
    }


    public ResponseEntity error(HttpStatus httpStatus, IResponseStatus status) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseStatus.setCode(status.getCode());
        responseStatus.setMessage(status.getMessage());
        responseObject.setStatus(responseStatus);
        ResponseEntity<?> responseEntity = new ResponseEntity(responseObject, httpStatus);
        return responseEntity;
    }

    public ResponseEntity error(HttpStatus httpStatus, IResponseStatus status, Object data) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseStatus.setCode(status.getCode());
        responseStatus.setMessage(status.getMessage());
        responseObject.setStatus(responseStatus);
        responseObject.setData(data);
        ResponseEntity<?> responseEntity = new ResponseEntity(responseObject, httpStatus);
        return responseEntity;
    }

    public ResponseEntity<?> buildResponse(HttpStatus httpStatus, ResponseStatus status) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseStatus.setCode(status.getCode());
        responseStatus.setMessage(status.getMessage());
        responseObject.setStatus(responseStatus);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(responseObject, httpStatus);
        return responseEntity;
    }

    public ResponseEntity<?> buildResponse(HttpStatus httpStatus, ResponseStatus status, Object data) {
        ResponseStatus responseStatus = new ResponseStatus();
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseStatus.setCode(status.getCode());
        responseStatus.setMessage(status.getMessage());
        responseObject.setStatus(responseStatus);
        responseObject.setData(data);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(responseObject, httpStatus);
        return responseEntity;
    }
}
