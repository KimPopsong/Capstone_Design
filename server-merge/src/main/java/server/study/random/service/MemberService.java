package server.study.random.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.study.random.config.security.JwtTokenProvider;
import server.study.random.domain.Member;
import server.study.random.advice.exception.MemberException;
import server.study.random.model.member.request.MemberLoginRequest;
import server.study.random.model.member.request.MemberSaveRequest;
import server.study.random.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Member save(MemberSaveRequest memberSaveRequest) {

        validateDuplicatedMemberByEmail(memberSaveRequest.getEmail());

        Member member = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getName(),
                                            passwordEncoder.encode(memberSaveRequest.getPassword()));

        return memberRepository.save(member);
    }

    public String login(MemberLoginRequest memberLoginRequest) {

        Member findMember = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(() -> new MemberException(memberLoginRequest.getEmail() + "은 존재하지 않는 회원입니다."));

        validateMemberPassword(memberLoginRequest.getPassword(), findMember.getPassword());

        return jwtTokenProvider.createToken(String.valueOf(findMember.getId()));
    }

    private void validateMemberPassword(String requestPassword, String memberPassword) {
        if(!passwordEncoder.matches(requestPassword, memberPassword)){
            throw new MemberException("패스워드가 일치하지 않습니다.");
        }
    }

    private void validateDuplicatedMemberByEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberException(email + "은 이미 존재하는 이메일입니다.");
        }
    }
}
