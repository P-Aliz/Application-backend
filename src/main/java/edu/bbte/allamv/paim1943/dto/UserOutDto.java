package edu.bbte.allamv.paim1943.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Valid
public class UserOutDto {
    private String id;
    private String email;
    private Integer level;
    private Integer all_points;
    private Integer current_points;
}
