package com.chinaredstar.po;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by CC on 16/3/29.
 */

@ApiModel(value = "TestUserPo", description = "用户传输对象")
public class TestUserPo implements Serializable {

    private int id;

    @NotEmpty(message = "名称不能为空")
    private String name;

    @Length(min = 11, max = 11, message = "电话必须11个长度")
    private String mobile;

    @ApiModelProperty("主键ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ApiModelProperty("名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty("手机号")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
