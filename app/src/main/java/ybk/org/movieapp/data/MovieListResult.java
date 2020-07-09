package ybk.org.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResult
{
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
    private List<MovieItem> result = null;

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

    public List<MovieItem> getResult() {
        return result;
    }

    public void setResult(List<MovieItem> result) {
        this.result = result;
    }
}
