package org.bigdata.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

public class ErrorMessage {
    /**
     * 状态码
     */
    int status;
    /**
     * 精要错误信息
     */
    String message;
    /**
     * 详细错误信息
     */
    String developerMessage;

    public ErrorMessage(NotFoundException ex){
        this.status = Response.Status.NOT_FOUND.getStatusCode();
        this.message = ex.getMessage();
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
