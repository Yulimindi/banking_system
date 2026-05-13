package com.example.demo.company.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.company.dto.CompanyUserDTO;
import com.example.demo.company.service.RegisterCompanyService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class RegisterCompanyController {

	private final RegisterCompanyService service;
	
	@GetMapping("/companyRegisterPage")
	public String 기업회원가입페이지() {
		System.out.println("기업회원가입페이지 이동 컨트롤러");
		return "company/companyRegister";
	}
	
	@ResponseBody
	@PostMapping("/companyRegisterPro")
	public int 사업자번호조회(@RequestBody Map<String,String> business_no) {
		System.out.println(business_no.get("business_no"));
		if(service.selectBno(business_no.get("business_no")) == true) {
			return 200;
		}
		return 0;
	}
	@PostMapping("/companyrPro")
	public void 기업직원회원가입(CompanyUserDTO dto) {
		System.out.println("회사개인 정보 : "+dto.toString());
	}
}
