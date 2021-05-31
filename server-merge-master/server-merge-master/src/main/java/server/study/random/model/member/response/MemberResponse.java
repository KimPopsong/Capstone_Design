package server.study.random.model.member.response;

import lombok.Data;
import server.study.random.domain.Member;

@Data
public class MemberResponse {

    private Long id;

    private String email;

    private String name;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
    }
}
