package com.yuan.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "相应的vo对象", description = "封装接口返回给前端的数据")
public class ResultVo {
    @ApiModelProperty(value = "响应状态码", dataType = "int")
    private int code;

    @ApiModelProperty(value = "响应提示信息", dataType = "String")
    private String msg;

    @ApiModelProperty(value = "响应数据", dataType = "Object")
    private Object data;
}
