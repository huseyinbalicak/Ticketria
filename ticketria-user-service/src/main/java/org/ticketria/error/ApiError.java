package org.ticketria.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Map;
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ApiError {

    private int statusCode;
    private String message;
    private String path;
    private long timestamp=new Date().getTime();
    private Map<String, String> validationErrors=null;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
