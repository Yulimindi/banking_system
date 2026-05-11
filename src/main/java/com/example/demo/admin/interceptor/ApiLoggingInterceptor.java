package com.example.demo.admin.interceptor;

import com.example.demo.admin.dao.IAdminLogDao;
import com.example.demo.admin.dto.AdminLogDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class ApiLoggingInterceptor implements HandlerInterceptor {

    private final IAdminLogDao adminLogDao;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        request.setAttribute("startTime", System.currentTimeMillis());


        return true;
    }
}