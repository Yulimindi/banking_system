package com.example.demo.admin.controller;

import com.example.demo.admin.dao.IAdminDao;
import com.example.demo.admin.dto.AdminDto;
import com.example.demo.admin.service.AdminMergeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminRegisterController {

    private final AdminMergeService serv;
    private final IAdminDao dao;



    @GetMapping("/adminJoin")
    public String joinPage(Model model) {

        model.addAttribute("admin", new AdminDto());

        return "admin/adminJoin";
    }

    @PostMapping("/admin/join")
    public String join(@ModelAttribute AdminDto dto) {
        int result = serv.join(dto);  // ✅ dao → serv 로 변경
        System.out.println("result = " + result);
        return "redirect:/adminLogin";
    }


    @GetMapping("/adminLogin")
    public String loginPage(Model model) {
        model.addAttribute("admin", new AdminDto());

        return "admin/adminLogin";
    }

    @PostMapping("/admin/login")
    public String login(@ModelAttribute AdminDto dto,
                        Model model,
                        HttpSession session) {  // ✅ 파라미터 추가

        AdminDto result = serv.login(dto);

        if (result == null) {
            model.addAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
            return "admin/adminLogin";
        }
        session.setAttribute("loginAdmin", result);  // ✅ 세션 저장
        return "redirect:/adminMain";  // 리다이렉트 그대로 OK
    }


}
