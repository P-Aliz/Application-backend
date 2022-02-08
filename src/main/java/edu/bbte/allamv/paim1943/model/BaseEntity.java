package edu.bbte.allamv.paim1943.model;

import com.arangodb.springframework.annotation.ArangoId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BaseEntity {
    @Id
    private String id;

    @com.arangodb.springframework.annotation.ArangoId
    private String ArangoId;
}
