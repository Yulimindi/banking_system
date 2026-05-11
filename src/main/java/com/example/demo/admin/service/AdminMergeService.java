package com.example.demo.admin.service;

import com.example.demo.admin.dao.IAdminActionDao;
import com.example.demo.admin.dao.IAdminDao;
import com.example.demo.admin.dto.AdminActionLogDto;
import com.example.demo.admin.dto.AdminDto;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminMergeService {

    private final IAdminDao dao;
    private final IAdminActionDao actionDao;

    // 관리자 회원가입 - 비밀번호 암호화
    public int join(AdminDto dto) {
        String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        dto.setPassword(hashed);  // ✅ 암호화된 비밀번호로 교체 후 저장
        return dao.join(dto);
    }

    // 관리자 로그인 - BCrypt로 비밀번호 검증
    public AdminDto login(AdminDto dto) {
        AdminDto admin = dao.login(dto);  // DB에서 아이디로 조회

        if (admin == null) {
            return null;  // ✅ 예외 대신 null 반환 → 컨트롤러에서 처리
        }

        if (!BCrypt.checkpw(dto.getPassword(), admin.getPassword())) {
            return null;  // ✅ 비밀번호 불일치도 null 반환
        }

        return admin;  // ✅ 로그인 성공
    }

    // 내 정보 조회
    public AdminDto selectMyPage(AdminDto dto) {
        return dao.selectMyPage(dto);
    }

    // 내 정보 수정 - 비밀번호 변경 시 암호화
    public int updateMyPage(AdminDto dto) {
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
            dto.setPassword(hashed);  // ✅ 수정 시에도 암호화
        }
        return dao.updateMyPage(dto);
    }
}