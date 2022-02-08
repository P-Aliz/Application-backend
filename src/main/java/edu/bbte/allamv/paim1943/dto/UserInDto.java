package edu.bbte.allamv.paim1943.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Valid
public class UserInDto {
    @NotNull
    private String id;
    @NotNull
    private String password;
    @NotNull
    private String password2;
    @NotNull
    @Email
    private String email;
}
