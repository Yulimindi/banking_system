package com.example.demo.company.dto;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CompanyUserDTO {
	//외래키
	private int company_id;
	private String login_id;
	private String password;
	private String name;
	private String phone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String birth;
	private String status;
	private String email;
	private String gender;
	private String e_name;
	private String business_no;
	private int account_pw;
	         
}
