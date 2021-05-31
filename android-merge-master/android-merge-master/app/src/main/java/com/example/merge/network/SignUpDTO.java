package com.example.merge.network;

/**
 * 회원가입 할 때에는 이름, 이메일, 비밀번호 순서!!!!!!!!!!!!!!!!!!!!!!!
 */
public class SignUpDTO {
    private final String name;
    private final String email;
    private final String password;

    public SignUpDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
