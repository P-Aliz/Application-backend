package edu.bbte.allamv.paim1943.model;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("problems")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Problem extends BaseEntity{
    private String title;
    private Integer time;
    private String description;
    private String answer;
    private Integer point;
}

