package com.yuan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin {
    /**
     * 编号
     */
    @Id
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 账户名
     */
    @Column(name = "admin_name")
    private String adminName;

    /**
     * 状态
     */
    @Column(name = "admin_state")
    private String adminState;

    /**
     * 密码
     */
    @Column(name = "admin_password")
    private String adminPassword;

    /**
     * 类型
     */
    @Column(name = "admin_type")
    private String adminType;

    /**
     * 注册时间
     */
    @Column(name = "admin_instime")
    private String adminInstime;

}