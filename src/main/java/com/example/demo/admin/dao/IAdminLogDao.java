package com.example.demo.admin.dao;

import com.example.demo.admin.dto.AdminActionLogDto;
import com.example.demo.admin.dto.AdminLogDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IAdminLogDao {
    int insertLog(AdminLogDto adminLogDto);
}
