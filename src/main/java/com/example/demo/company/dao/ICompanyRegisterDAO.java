package com.example.demo.company.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.company.dto.CompanyUserDTO;
import com.example.demo.personal.dto.Accounts_personalDTO;

@Mapper
public interface ICompanyRegisterDAO {

	// 사업자 번호 조희
	int selectBno(@Param("business_no") String bno);

	int insertCompanyUser(CompanyUserDTO dto);
	int checkId(@Param("Login_id") String Login_id);

}
