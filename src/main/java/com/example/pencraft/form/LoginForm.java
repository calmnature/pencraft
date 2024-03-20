package com.example.pencraft.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {

    @NotEmpty(message = "사번을 입력하세요")
    private String employees_id;
    @NotEmpty(message = "비밀번호를 입력하세요")
    private String password;
}
