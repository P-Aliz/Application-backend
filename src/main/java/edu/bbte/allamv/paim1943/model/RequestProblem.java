package edu.bbte.allamv.paim1943.model;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("requestproblems")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RequestProblem extends BaseEntity{
    private String title;
    private String text;
    private String answer;
    private String lesson;
    private String requester;
}
