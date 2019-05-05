package com.github.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author : JieWang
 * @Date : Created in 2019年05月05日13:57
 * @Email : wangjie_hf@seczone.cn
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "JsonResult<T>", description = "Response JsonResult")
public class JsonResult<T> implements Serializable {

    @ApiModelProperty(name = "status", value = "0:error ;1:success", dataType = "Integer")
    private int status;

    @ApiModelProperty(name = "data", value = "response data")
    private T data;

    @ApiModelProperty(name = "message", value = "response message", dataType = "String")
    private String msg;

    private JsonResult(int status) {
        this.status = status;
    }

    private JsonResult(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private JsonResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private JsonResult(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    // 使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    // 成功响应静态方法组
    public static <T> JsonResult<T> createBySuccess() {
        return new JsonResult<T>(ResponseCode.SUCCESS.getCode());
    }


    public static <T> JsonResult<T> createBySuccessMessage(String msg) {
        return new JsonResult<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> JsonResult<T> createBySuccess(T data) {
        return new JsonResult<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> JsonResult<T> createBySuccess(String msg, T data) {
        return new JsonResult<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }


    // 信息提示响应结果方法组
    public static <T> JsonResult<T> createByFormValid(String msg) {
        return new JsonResult<T>(ResponseCode.VALID_TIP.getCode(), msg);
    }

    public static <T> JsonResult<T> createByFormValid(String msg, T data) {
        return new JsonResult<T>(ResponseCode.VALID_TIP.getCode(), msg, data);
    }


    // 失败响应结果方法组
    public static <T> JsonResult<T> createByError() {
        return new JsonResult<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDescription());
    }

    public static <T> JsonResult<T> createByErrorMessage(String errorMessage) {
        return new JsonResult<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> JsonResult<T> createByNeedLogin(String errorMessage) {
        return new JsonResult<T>(ResponseCode.NEED_LOGIN.getCode(), errorMessage);
    }

    public static <T> JsonResult<T> createByNeedLogin() {
        return new JsonResult<T>(ResponseCode.NEED_LOGIN.getCode(), ResponseMsg.LOGIN_AGAIN);
    }

    public static <T> JsonResult<T> createByAgentGlobalDisable(String errorMessage) {
        return new JsonResult<T>(ResponseCode.AGENT_GLOBAL_ERROR.getCode(), errorMessage);
    }
    public static <T> JsonResult<T> createByIllegalMessage(String errorMessage) {
        return new JsonResult<T>(ResponseCode.ILLEGAL_ARGUMENT.getCode(), errorMessage);
    }

    public static <T> JsonResult<T> createByLicenseErrorMessage(String errorMessage) {
        return new JsonResult<T>(ResponseCode.LICESE_ERROR.getCode(), errorMessage);
    }

}
