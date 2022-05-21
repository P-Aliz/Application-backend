package edu.bbte.allamv.paim1943.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BaseRelation {
    private String edge_id;

    /*@com.arangodb.springframework.annotation.ArangoId
    private String _to;*/
}
