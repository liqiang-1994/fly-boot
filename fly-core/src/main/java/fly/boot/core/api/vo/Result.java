package fly.boot.core.api.vo;

import cn.hutool.http.HttpStatus;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private boolean success = true;

    private String message = "";

    private Integer code = 0;

    private T result;

    private long timestamp = System.currentTimeMillis();

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

    public Result<T> success(String message) {
        this.message = message;
        this.code = HttpStatus.HTTP_OK;
        this.success = true;
        return this;
    }

    public static<T> Result<T> OK() {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(HttpStatus.HTTP_OK);
        return r;
    }

    public static<T> Result<T> OK(T data) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(HttpStatus.HTTP_OK);
        r.setResult(data);
        return r;
    }

    public static<T> Result<T> OK(String msg) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(HttpStatus.HTTP_OK);
        r.setResult((T) msg);
        r.setMessage(msg);
        return r;
    }

    public static<T> Result<T> OK(T data, String msg) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(HttpStatus.HTTP_OK);
        r.setResult(data);
        r.setMessage(msg);
        return r;
    }

    public static<T> Result<T> error(String msg, T data) {
        Result<T> r = new Result<>();
        r.setSuccess(false);
        r.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        r.setResult(data);
        r.setMessage(msg);
        return r;
    }

    public static<T> Result<T> error(int code, String msg) {
        Result<T> r = new Result<>();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static<T> Result<T> error(String msg) {
        return error(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }
}
