package ybk.org.movieapp.data.local.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("resultType")
    @Expose
    private String resultType;

    @SerializedName("result")
    @Expose
    private String result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", resultType='" + resultType + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
