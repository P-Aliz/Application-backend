package edu.bbte.allamv.paim1943.dto;

import com.arangodb.springframework.annotation.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import java.util.List;

@Data
@Valid
@Document("users")
public class UserOutDto {
    @Id
    private String id;
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
