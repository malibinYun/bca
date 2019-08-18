package com.malibin.boostcourseace.network.response;

/**
 * Created By Yun Hyeok
 * on 8월 17, 2019
 */

public class ResponseTemplate<T> {
    private String message;
    private int code;
    private String resultType;
    private int totalCount;
    private T result;

    public ResponseTemplate() {
    }

    public ResponseTemplate(String message, int code, String resultType, int totalCount, T result) {
        this.message = message;
        this.code = code;
        this.resultType = resultType;
        this.totalCount = totalCount;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getResultType() {
        return resultType;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public T getResult() {
        return result;
    }
}
