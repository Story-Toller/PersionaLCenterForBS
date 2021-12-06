package com.yuan.controller;

import com.yuan.entity.Admin;
import com.yuan.service.AdminService;
import com.yuan.utils.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Api(value = "提供登录和注册接口", tags = "Admin管理")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "adminName", value = "登录名", required = true),
            @ApiImplicitParam(dataType = "String", name = "adminPassword", value = "登录密码", required = true)
    })
    @GetMapping("/login")
    public ResultVo login(@RequestParam("adminName") String adminName,
                          @RequestParam(value = "adminPassword") String adminPassword) {
        ResultVo resultVo = adminService.checkLogin(adminName, adminPassword);
        return resultVo;
    }

    @ApiOperation("注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "adminName", value = "注册用户名", required = true),
            @ApiImplicitParam(dataType = "String", name = "adminPassword", value = "注册密码", required = true)
    })
    @PostMapping("/register")
    public ResultVo register(@RequestBody Admin admin) {
        ResultVo resultVo = adminService.adminRegister(admin.getAdminName(), admin.getAdminPassword());
        return resultVo;
    }

}
