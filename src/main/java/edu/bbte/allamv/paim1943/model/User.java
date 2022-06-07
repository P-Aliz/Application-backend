package edu.bbte.allamv.paim1943.model;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{
    private String password;
    private String email;
    private Integer level;
    private Integer all_points;
    private Integer current_points;
    private String avatar;
    private Integer animal_nr;
    private Integer badge_nr;
    private Integer friends_nr;
    private List<String> resolvedproblems;
    private Boolean admin;
    private List<String> badges;
}

