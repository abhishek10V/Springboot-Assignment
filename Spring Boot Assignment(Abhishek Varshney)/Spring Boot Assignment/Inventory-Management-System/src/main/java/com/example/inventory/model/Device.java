package com.example.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor

public class Device {

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public List<ShelfPositionVO> getShelfPosition() {
        return shelfPos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setShelfPosition(List<ShelfPositionVO> shelfPosition) {
        this.shelfPos = shelfPosition;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String deviceType;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private List<ShelfPositionVO> shelfPos;
}
