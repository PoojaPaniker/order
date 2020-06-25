package com.abn.assignment.order.exceptions;

import java.util.Date;

public class CustomErrorDetails {

    private Date date;
    private String message;
    private String error;

    public CustomErrorDetails(Date date, String message, String error) {
        this.date = date;
        this.message = message;
        this.error = error;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}
