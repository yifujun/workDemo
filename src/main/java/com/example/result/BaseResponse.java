package com.example.result;

import java.io.Serializable;

/**
 * 接口层统一返回的数据封装接口.
 */
public class BaseResponse implements Serializable {


    /**
     * 响应业务状态(200.成功，-1.失败,3.session过期).
     */
    private int code;

    /**
     * 响应消息.
     */
    private String message;

    /**
     * 响应中的数据.
     */
    private Object data;

    /**
     * success.
     */
    private boolean success;

    /**
     * success.
     */
    public static BaseResponse success() {
        return new BaseResponse(true);
    }

    /**
     * success.
     */
    public static BaseResponse success(Object data) {
        return new BaseResponse(true, data);
    }

    /**
     * success.
     */
    public static BaseResponse success(String message) {
        return BaseResponse.success().message(message);
    }

    /**
     * success.
     */
    public static BaseResponse success(Object data, String message) {
        return BaseResponse.success().data(data).message(message);
    }

    /**
     * failure.
     */
    public static BaseResponse failure() {
        return new BaseResponse(false);
    }

    /**
     * failure.
     */
    public static BaseResponse failure(String message) {
        return BaseResponse.failure().message(message);
    }

    /**
     * 功能描述:
     * failure（状态码、返回描述信息）.
     *
     * @methodname: failure
     * @params: [code, message]
     * @returns: com.metlife.dgt.graphic.modules.result.BaseResponse
     * @author: somnus
     * @date: 2019-11-16 19:29:19
     */
    public static BaseResponse failure(Integer code, String message) {
        return BaseResponse.failure().code(code).message(message);
    }

    /**
     * failure.
     */
    public static BaseResponse failure(Object data, String message) {
        return BaseResponse.failure().data(data).message(message);
    }

    /**
     * failure.
     */
    public static BaseResponse failure(BaseEnum baseEnum) {
        return BaseResponse.failure().code(baseEnum.getCode()).message(baseEnum.getDesc());
    }

    /**
     * BaseResponse.
     */
    public BaseResponse(boolean success, int code, String message, Object data) {
        super();
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * BaseResponse.
     */
    public BaseResponse(boolean success) {
        super();
        this.success = success;
        this.code = success ? 200 : -1;
        this.message = success ? "成功" : "失败";
    }

    /**
     * BaseResponse.
     */
    public BaseResponse(boolean success, Object data) {
        super();
        this.success = success;
        this.data = data;
        this.code = success ? 200 : -1;
        this.message = success ? "成功" : "失败";
    }

    /**
     * BaseResponse.
     */
    public BaseResponse(boolean success, String message, Object data) {
        this(success, success ? 200 : -1, message, data);
    }

    /**
     * 功能描述:
     * 构造（状态码、返回描述信息）.
     *
     * @methodname: BaseResponse
     * @params: [code, message]
     * @returns:
     * @author: somnus
     * @date: 2019-11-16 19:30:24
     */
    public BaseResponse(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * isSuccess.
     */
    public Boolean isSuccess() {
        return success;
    }

    /**
     * isSuccess.
     */
    public BaseResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * getCode.
     */
    public int getCode() {
        return code;
    }

    /**
     * code.
     */
    public BaseResponse code(int code) {
        this.code = code;
        return this;
    }

    /**
     * getMessage.
     */
    public String getMessage() {
        return message;
    }

    /**
     * message.
     */
    public BaseResponse message(String message) {
        this.message = message;
        return this;
    }

    /**
     * getData.
     */
    public Object getData() {
        return data;
    }

    /**
     * data.
     */
    public BaseResponse data(Object data) {
        this.data = data;
        return this;
    }
}
