package com.example.demo.commonLogin.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.commonLogin.dao.ILoginDAO;
import com.example.demo.commonLogin.dto.UserLoginDTO;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.personal.service.LoginAndRegisterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

	private final ILoginDAO loginDAO;
	private final JwtUtil jwt;
	private final LoginAndRegisterService service;

	public Map<String, String> login(UserLoginDTO dto) {

		if (dto == null) {
			return Map.of("result", "fail");
		}

		log.debug("로그인 서비스 파일 - ID: {}", dto.getLogin_id());

		UserLoginDTO getUserDTO = loginDAO.selectUser(dto.getLogin_id());

		if (getUserDTO != null) {
			if (service.matchesPassword(dto.getSecu_pw(), getUserDTO.getSecu_pw())) {

				getUserDTO.setRole("user");
				String token = jwt.generateToken(getUserDTO.getUsr_nm(), getUserDTO.getRole());
				log.info("개인 회원 로그인 서비스 성공");
				return Map.of("name", getUserDTO.getUsr_nm(), "token", token, "role", getUserDTO.getRole());
			}

			log.error("로그인 실패: 개인회원 비밀번호 불일치  ID: {}", dto.getLogin_id());
			return Map.of("result", "fail");
		}

		UserLoginDTO getComUserDTO = loginDAO.selectCompanyUser(dto.getLogin_id());

		if (getComUserDTO != null) {

			if (service.matchesPassword(dto.getSecu_pw(), getComUserDTO.getSecu_pw())) {
				getComUserDTO.setRole("company");
				String token = jwt.generateToken(getComUserDTO.getUsr_nm(), getComUserDTO.getRole());
				log.info("기업 회원 로그인 서비스 성공");
				return Map.of("name", getComUserDTO.getUsr_nm(), "token",  token, "role",getComUserDTO.getRole());
			}

			log.error("로그인 실패: 기업회원 비밀번호 불일치  ID: {}", dto.getLogin_id());
			return Map.of("result", "fail");
		}

		log.error("로그인 실패: 존재하지 않는 아이디 로그인 시도 : {}", dto.getLogin_id());
		return Map.of("result", "fail");
	}
}