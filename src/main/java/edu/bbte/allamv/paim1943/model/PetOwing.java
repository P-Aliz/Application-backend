package edu.bbte.allamv.paim1943.model;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PetOwing extends BaseRelation{
    private String pet_id;
    private String img;
    private Integer minim_point;
    private Integer feed_point;
    private Integer pet_point;
    private String name;
    private Integer food_percentage;
    private Integer happynes_percenytage;
}

