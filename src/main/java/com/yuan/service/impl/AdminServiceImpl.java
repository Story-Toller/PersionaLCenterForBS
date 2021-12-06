package com.yuan.service.impl;

import com.yuan.dao.AdminMapper;
import com.yuan.entity.Admin;
import com.yuan.service.AdminService;
import com.yuan.utils.MD5Utils;
import com.yuan.utils.ResStatus;
import com.yuan.utils.ResultVo;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public ResultVo adminRegister(String adminName, String adminPassword) {
        synchronized (this) {
//        根据用户查询，这个用户是否被注册
            Example example = new Example(Admin.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("adminName", adminName);
            List<Admin> admins = adminMapper.selectByExample(example);

            if (admins.size() == 0) {
                String md5pwd = MD5Utils.md5(adminPassword);
                Admin admin = new Admin();
                admin.setAdminName(adminName);
                admin.setAdminPassword(md5pwd);
                admin.setAdminInstime(s.format(new Date()));
                int insert = adminMapper.insert(admin);
                if (insert > 0) {
                    return new ResultVo(ResStatus.OK, "注册成功", null);
                } else {
                    return new ResultVo(ResStatus.NO, "注册失败", null);
                }
            } else {
                return new ResultVo(ResStatus.NO, "用户名已被占用", null);
            }
        }
    }

    @Override
    public ResultVo checkLogin(String adminName, String adminPassword) {
        //        根据用户查询，这个用户是否被注册
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adminName", adminName);
        List<Admin> admins = adminMapper.selectByExample(example);
        if (admins.size() == 0) {
            return new ResultVo(ResStatus.NO, "登陆失败,用户名不存在", null);
        } else {
            String s = MD5Utils.md5(adminPassword);
            if (s.equals(admins.get(0).getAdminPassword())) {
                JwtBuilder builder = Jwts.builder();
                HashMap<String, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("key1", "value1");
                objectObjectHashMap.put("key2", "value2");
                objectObjectHashMap.put("key3", "value3");

                String token = builder.setSubject(admins.get(0).getAdminName())           //设置token中携带的数据
                        .setIssuedAt(new Date())                            //设置token生成时间
                        .setId(admins.get(0).getAdminId() + "")            //设置用户id
                        .setClaims(objectObjectHashMap)
                        .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))//设置token过期时间
                        .signWith(SignatureAlgorithm.HS512, "syx12138asdf")     //设置token解密密码
                        .compact();
                return new ResultVo(ResStatus.OK, token, admins.get(0));
            } else {
                return new ResultVo(ResStatus.NO, "登陆失败,密码错误", null);
            }
        }
    }
}
