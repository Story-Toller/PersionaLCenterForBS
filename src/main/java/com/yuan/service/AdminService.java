package com.yuan.service;


import com.yuan.utils.ResultVo;

public interface AdminService {

    //    用户注册
    ResultVo adminRegister(String adminName, String adminPassword);

    //    用户登录
    ResultVo checkLogin(String adminName, String adminPassword);
}
