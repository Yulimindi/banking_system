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
<<<<<<< Updated upstream
    private final JwtUtil jwtUtil;
    private final IAdminActionDao actionDao;

=======
    //private final JwtUtil jwtUtil;
    private final IAdminActionDao actionDao;
>>>>>>> Stashed changes

    @GetMapping("/adminJoin")
    public String joinPage(Model model) {
        model.addAttribute("admin", new AdminDto());
        return "admin/adminJoin";
    }

    @PostMapping("/admin/join")
    public String join(@ModelAttribute AdminDto dto) {
<<<<<<< Updated upstream
        int result = serv.join(dto);  // ✅ dao → serv 로 변경

        actionDao.insertActionLog(
                new AdminActionLogDto(
                        "UPDATE_USER",
                        "user_id=3"
                )
        );


=======
        int result = serv.join(dto);
        actionDao.insertActionLog(new AdminActionLogDto("UPDATE_USER", "user_id=" + dto.getAdmin_id()));
>>>>>>> Stashed changes
        System.out.println("result = " + result);
        return "redirect:/adminLogin"; // 가입 후 로그인 페이지로 리다이렉트
    }

    @GetMapping("/adminLogin")
    public String loginPage(Model model) {
        model.addAttribute("admin", new AdminDto());
        return "admin/adminLogin";
    }

<<<<<<< Updated upstream
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
=======
//    @PostMapping("/admin/login") // 로그인 엔드포인트
//    @ResponseBody
//    public ResponseEntity<?> login(@RequestBody AdminDto dto, HttpSession session) {
//        AdminDto result = serv.login(dto);
//
////        if (result == null) {
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
////                    .body("로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요.");
////        }
////
////        String accessToken = jwtUtil.createAccessToken(result.getAdmin_id());
////        String refreshToken = jwtUtil.createRefreshToken(result.getAdmin_id());
////
////        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
//
//            // 로그인 실패 시 응답
//            if (result == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body("아이디 또는 비밀번호가 올바르지 않습니다.");
//            }
//
//            // 로그인 성공 시 세션에 사용자 정보 저장
//            session.setAttribute("loginUser", result);
//            return ResponseEntity.ok("로그인 성공");
//    }



@PostMapping("/admin/login")
public String login(@ModelAttribute AdminDto dto,
                    HttpSession session) {

    AdminDto admin = serv.login(dto);

    System.out.println(admin);

    // 로그인 성공
    if(admin != null) {

        session.setAttribute(
                "adminId",
                admin.getAdmin_id()
        );

        session.setAttribute(
                "loginId",
                admin.getLogin_id()
        );

        session.setAttribute(
                "name",
                admin.getName()
        );

        System.out.println("Session ID: " + session.getId());


        return "redirect:/admin/adminMain";
    }

    // 로그인 실패
    return "redirect:/adminLogin";
}



    @GetMapping("/adminMyPage")
    public String myPage(HttpSession session,
                         Model model) {

        Long adminId =
                (Long) session.getAttribute("adminId");

        System.out.println(adminId);

        // 로그인 안 됨
        if(adminId == null) {
            return "redirect:/admin/login";
        }

        AdminDto dto = new AdminDto();
        dto.setAdmin_id(adminId);

        AdminDto admin =
                serv.selectMyPage(dto);

        model.addAttribute("admin", admin);

        return "admin/adminMyPage";
    }

    @PostMapping("/admin/update")
    public String updateMyPage(@ModelAttribute AdminDto dto) {

        serv.updateMyPage(dto);

        return "redirect:/admin/adminMyPage";
>>>>>>> Stashed changes
    }




}

