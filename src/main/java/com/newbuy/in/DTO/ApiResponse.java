package com.newbuy.in.DTO;

public class ApiResponse<T> {
    private boolean sts;
    private T data;
    private String message;

    public ApiResponse() {}

    public ApiResponse(boolean sts, T data) {
        this.sts = sts;
        this.data = data;
    }

    public ApiResponse(boolean sts, T data, String message) {
        this.sts = sts;
        this.data = data;
        this.message = message;
    }

    // getters and setters
    public boolean isSts() { return sts; }
    public void setSts(boolean sts) { this.sts = sts; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
