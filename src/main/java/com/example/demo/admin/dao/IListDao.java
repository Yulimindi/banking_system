package com.example.demo.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.admin.dto.ProductDto;

@Mapper
public interface IListDao {
	AdminDto getAdmin(@Param("admin_id") Long admin_id);
	List<AdminDto> getAdmins();
	UserDto getUser(@Param("user_id") Long user_id);
	List<UserDto> getUsers();
}
