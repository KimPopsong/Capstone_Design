package com.example.merge.network;

import com.example.merge.entities.Member;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberDTO {
    @SerializedName("member_id")
    @Expose
    private Long id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("name")
    @Expose
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member toMember() {
        return new Member(
                id,
                email,
                name
        );
    }
}
