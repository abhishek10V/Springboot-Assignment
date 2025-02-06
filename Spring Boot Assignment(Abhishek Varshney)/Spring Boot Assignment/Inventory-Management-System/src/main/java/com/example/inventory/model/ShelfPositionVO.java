package com.example.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor

public class ShelfPositionVO {
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long deviceId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDevice(Device device) {
        deviceId = device.getId();
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShelfVO getShelf() {
        return shelf;
    }

    public void setShelf(ShelfVO shelf) {
        this.shelf = shelf;
    }

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private ShelfVO shelf;
}
