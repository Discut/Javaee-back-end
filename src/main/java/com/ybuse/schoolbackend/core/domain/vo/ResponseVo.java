package com.ybuse.schoolbackend.core.domain.vo;

import com.ybuse.schoolbackend.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ResponseVo<T> {
    private Integer status;
    private String message;
    private T data;
    private String date;
    private String path = "";
    private HttpServletResponse response;

    public ResponseVo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(new Date());
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param <D>
     * @return ResponseVo
     */
    public static <D> ResponseVo<D> success(D data) {
        ResponseVo<D> responseVo = new ResponseVo<>();
        responseVo.setStatus(HttpStatus.OK.value());
        responseVo.setMessage("success");
        responseVo.setData(data);
        return responseVo;
    }

    /**
     * 未验证
     *
     * @param message 消息
     * @param <D>
     * @return ResponseVo
     */
    public static <D> ResponseVo<D> unauthorized(String message) {
        ResponseVo<D> responseVo = new ResponseVo<>();
        responseVo.setStatus(HttpStatus.UNAUTHORIZED.value());
        responseVo.setMessage(message);
        return responseVo;
    }

    /**
     * 服务器错误
     *
     * @param message 消息
     * @param <D>
     * @return ResponseVo
     */
    public static <D> ResponseVo<D> error(String message) {
        ResponseVo<D> responseVo = new ResponseVo<>();
        responseVo.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseVo.setMessage(message);
        return responseVo;
    }

    /**
     * 权限不足
     *
     * @param message 消息
     * @param <D>
     * @return ResponseVo
     */
    public static <D> ResponseVo<D> forbidden(String message) {
        ResponseVo<D> responseVo = new ResponseVo<>();
        responseVo.setStatus(HttpStatus.FORBIDDEN.value());
        responseVo.setMessage(message);
        return responseVo;
    }



    public Integer getStatus() {
        return status;
    }

    public ResponseVo<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseVo<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseVo<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getDate() {
        return date;
    }

    public ResponseVo<T> setDate(String date) {
        this.date = date;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ResponseVo<T> setPath(String path) {
        this.path = path;
        return this;
    }

    public ResponseVo<T> putIn(HttpServletResponse response) {
        this.response = response;
        return this;
    }

    public void send(HttpServletResponse response) throws IOException {
        ResponseUtil.returnJson(response, this);
    }

    public void send() throws IOException {
        if (Objects.isNull(response)) {
            throw new ResponseException("response is null");
        }
        send(response);
    }
}

class ResponseException extends RuntimeException {
    public ResponseException(String message) {
        super(message);
    }
}
