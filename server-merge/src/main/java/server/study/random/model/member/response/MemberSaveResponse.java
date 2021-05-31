package server.study.random.model.member.response;

import lombok.Data;
import server.study.random.domain.Member;

@Data
public class MemberSaveResponse {

    private Long id;
    private String name;
    private String email;
    private String role;

    public MemberSaveResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.role = member.getRole();
    }
}
