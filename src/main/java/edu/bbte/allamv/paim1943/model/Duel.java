package edu.bbte.allamv.paim1943.model;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
@Document("duels")
@Data
@Valid
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Duel extends BaseEntity{
    private String _from;
    private String _to;
    private Long date;
    private Boolean happened;
    private String winner;
    private String points;
    private Problem problem;
    private Boolean accepted;
}