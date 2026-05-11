package com.example.demo.admin.dao;

import com.example.demo.admin.dto.AdminActionLogDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminActionDao {
    int insertActionLog(AdminActionLogDto adminActionLogDto);
}
