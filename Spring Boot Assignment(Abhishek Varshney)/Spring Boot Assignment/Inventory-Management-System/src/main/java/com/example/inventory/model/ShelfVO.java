package com.example.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor

public class ShelfVO {
    public void setId(Long id) {
        this.id = id;
    }

    public void setShelfPositionId(Long shelfPositionId) {
        this.ShelfPositionId = shelfPositionId;
    }

    public void setShelfType(String shelfType) {
        this.shelfType = shelfType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getShelfPositionId() {
        return ShelfPositionId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShelfType() {
        return shelfType;
    }
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String shelfType;
    private Long ShelfPositionId;

}
