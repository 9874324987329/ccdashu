package com.chinaredstar.entity;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author:杨果
 * @date:15/12/21 下午2:24
 * <p/>
 * Description:
 */
@ApiModel(value = "结果DTO", description = "Resut请求结果")
public class Result {
    private int code;//code
    private Object value;//结果

    @ApiModelProperty(value = "结果代码")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @ApiModelProperty(value = "结果对象")
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
