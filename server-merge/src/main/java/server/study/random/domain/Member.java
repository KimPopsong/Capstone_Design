package server.study.random.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String name;

    private String password;

    private String role; // 인증과 인가

    public static Member createMember(String email,String name, String password){
        Member member = new Member();
        member.email = email;
        member.name = name;
        member.password = password;
        member.role = "ROLE_MEMBER";
        return member;
    }

}
