package edu.bbte.allamv.paim1943.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BaseRelation extends BaseEntity{
    private String _to;
    private String _from;
    private String edge_id;
}
