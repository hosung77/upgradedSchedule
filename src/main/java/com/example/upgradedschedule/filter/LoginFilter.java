package com.example.upgradedschedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/","/api/user/login","/api/user/signup","api/user/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");

        // 로그인을 체크 해야하는 URL인지 검사
        if (!isWhiteList(requestURI)){

            HttpSession session = httpRequest.getSession(false);

            if(session == null || session.getAttribute("sessionKey") == null){
                throw new RuntimeException("로그인 해주세요");
            }

            // 로그인 성공 로직
            log.info("로그인에 성공했습니다.");

        }

        // 1번 경우 : WHITE_LIST에 등록된 URL 요청이라면 chain.doFilter() 호출
        // 2번 경우 : WHITE_LIST에 등록된 URL 요청이 아니라면 위 필터로직을 통과 후에 chain.doFilter() 다음 필터나 Servlet이 호출된다.
        // 다음 필터가 없으면 Servlet -> Controller, 다음 필터가 있으면 다음 Filter가 호출된다.
        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
