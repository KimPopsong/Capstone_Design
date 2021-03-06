package server.study.random.controller;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.study.random.advice.exception.AuthenticationEntryPointException;


@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping("/entrypoint")
    public void entryPointException(){
        throw new AuthenticationEntryPointException("로그인이 필요한 서비스입니다.");
    }

    @RequestMapping("/accessdenied")
    public void accessDenied(){
        throw new AccessDeniedException("보유한 권한으로 접근할 수 없습니다.");
    }
}