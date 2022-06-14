package edu.bbte.allamv.paim1943.model;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("lessonsr")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LessonRequest extends BaseEntity{
    private String title;
    private String description;
    private String url;
    private Boolean hasProblem;
    private String img;
    private String requester;
}

