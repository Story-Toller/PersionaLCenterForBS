package com.yuan.dao;


import com.yuan.entity.Admin;
import com.yuan.utils.FamilyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends FamilyMapper<Admin> {
}