package com.gtvt.backendcustomermanagement.model;

public enum ManagementResponseStatus implements IResponseStatus {
    SUCCESS("success", "Success"),
    GENERAL_ERROR("general_error", "Any error occur"),
    DATA_BEING_USED("data_being_used", "DATA_BEING_USED"),
    USER_DISABLED("USER_DISABLED", "User disabled"),
    INVALID_INPUT("invalid_input", "Input value is invalid"),
    INVALID_DATA_TYPE("invalid_input", "Field %s is in invalid data type"),
    MODULE_ROLES_HAVE_BEEN_ADDED("MODULE_ROLE_HAS_BEEN_ADD", "Module roles have been added"),
    FUNCTION_ROLES_HAVE_BEEN_ADDED("FUNCTION_ROLES_HAVE_BEEN_ADDED", "Function roles have been added"),
    REQUEST_IS_NULL("REQUEST_IS_NULL", "Roles function request is null");




    private String code;
    private String message;

    ManagementResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ManagementResponseStatus fromCode(String code) {
        for (ManagementResponseStatus s : ManagementResponseStatus.values()) {
            if (s.getCode().equalsIgnoreCase(code)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
