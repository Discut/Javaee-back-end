package com.ybuse.schoolbackend.core.domain.vo;

import com.ybuse.schoolbackend.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Schema(description = "公共返回结果")
public class CommonResult<T> {
    @Schema(description = "状态码")
    private Integer status;
    @Schema(description = "消息")
    private String message;
    @Schema(description = "数据封装")
    private T data;
    @Schema(description = "执行时间")
    private String date;
    @Schema(description = "执行url路径")
    private String path = "";
    private HttpServletResponse response;

    public CommonResult() {
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
    public static <D> CommonResult<D> success(D data) {
        CommonResult<D> commonResult = new CommonResult<>();
        commonResult.setStatus(HttpStatus.OK.value());
        commonResult.setMessage("success");
        commonResult.setData(data);
        return commonResult;
    }

    /**
     * 未验证
     *
     * @param message 消息
     * @param <D>
     * @return ResponseVo
     */
    public static <D> CommonResult<D> unauthorized(String message) {
        CommonResult<D> commonResult = new CommonResult<>();
        commonResult.setStatus(HttpStatus.UNAUTHORIZED.value());
        commonResult.setMessage(message);
        return commonResult;
    }

    /**
     * 服务器错误
     *
     * @param message 消息
     * @param <D>
     * @return ResponseVo
     */
    public static <D> CommonResult<D> error(String message) {
        CommonResult<D> commonResult = new CommonResult<>();
        commonResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        commonResult.setMessage(message);
        return commonResult;
    }

    /**
     * 权限不足
     *
     * @param message 消息
     * @param <D>
     * @return ResponseVo
     */
    public static <D> CommonResult<D> forbidden(String message) {
        CommonResult<D> commonResult = new CommonResult<>();
        commonResult.setStatus(HttpStatus.FORBIDDEN.value());
        commonResult.setMessage(message);
        return commonResult;
    }



    public Integer getStatus() {
        return status;
    }

    public CommonResult<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getDate() {
        return date;
    }

    public CommonResult<T> setDate(String date) {
        this.date = date;
        return this;
    }

    public String getPath() {
        return path;
    }

    public CommonResult<T> setPath(String path) {
        this.path = path;
        return this;
    }

    public CommonResult<T> putIn(HttpServletResponse response) {
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
