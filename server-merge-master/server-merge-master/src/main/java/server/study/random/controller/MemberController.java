package server.study.random.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import server.study.random.domain.Member;
import server.study.random.model.member.request.MemberLoginRequest;
import server.study.random.model.member.request.MemberSaveRequest;
import server.study.random.model.member.response.MemberLoginResponseHaha;
import server.study.random.model.member.response.MemberSaveResponse;
import server.study.random.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/members")
    public MemberSaveResponse save(@RequestBody @Valid MemberSaveRequest memberSaveRequest){

        Member savedMember = memberService.save(memberSaveRequest);

        return new MemberSaveResponse(savedMember);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/members/login")
    public void login(@RequestBody @Valid MemberLoginRequest memberLoginRequest, HttpServletResponse response) throws UnsupportedEncodingException {
        String token = memberService.login(memberLoginRequest);

        Cookie cookie = new Cookie("token", URLEncoder.encode(token, "UTF-8"));
        cookie.setPath("/");

        response.addCookie(cookie);
    }

}
