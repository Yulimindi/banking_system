package com.example.demo.admin.controller;

import com.example.demo.admin.dao.IAdminActionDao;
import com.example.demo.admin.dao.IAdminDao;
import com.example.demo.admin.dto.AdminActionLogDto;
import com.example.demo.admin.dto.AdminDto;
import com.example.demo.admin.interceptor.JwtUtil;
import com.example.demo.admin.interceptor.TokenResponse;
import com.example.demo.admin.service.AdminMergeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminRegisterController {

    private final AdminMergeService serv;
    private final IAdminDao dao;
    private final JwtUtil jwtUtil;
    private final IAdminActionDao actionDao;


    @GetMapping("/adminJoin")
    public String joinPage(Model model) {

        model.addAttribute("admin", new AdminDto());

        return "admin/adminJoin";
    }

    @PostMapping("/admin/join")
    public String join(@ModelAttribute AdminDto dto) {
        int result = serv.join(dto);  // ✅ dao → serv 로 변경

        actionDao.insertActionLog(
                new AdminActionLogDto(
                        "UPDATE_USER",
                        "user_id=3"
                )
        );


        System.out.println("result = " + result);
        return "redirect:/adminLogin";
    }


    @GetMapping("/adminLogin")
    public String loginPage(Model model) {
        model.addAttribute("admin", new AdminDto());

        return "admin/adminLogin";
    }

    @PostMapping("/admin/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody AdminDto dto) {
        AdminDto result = serv.login(dto);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요."); // 사용자에게 구체적 메시지 반환
        }

        String accessToken = jwtUtil.createAccessToken(result.getAdmin_id());
        String refreshToken = jwtUtil.createRefreshToken(result.getAdmin_id());

        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }




}
