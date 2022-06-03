package edu.bbte.allamv.paim1943.model;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("lessons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Lesson extends BaseEntity{
    private String title;
    private String description;
    private String url;
    private Boolean hasProblem;
}

