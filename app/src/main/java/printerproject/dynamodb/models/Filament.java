package printerproject.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "FilamentsTable")
public class Filament {
    private String filamentId;
    private String color;
    private String material;
    private String isActive;
    private Integer materialRemaining;


    @DynamoDBHashKey(attributeName = "filamentId")
    public String getFilamentId() {
        return filamentId;
    }

    public void setFilamentId(String filamentId) {
        this.filamentId = filamentId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "FilamentsSortByColorIndex", attributeName = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @DynamoDBAttribute(attributeName = "material")
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "FilamentsSortByColorIndex", attributeName = "isActive")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String active) {
        isActive = active;
    }

    @DynamoDBAttribute(attributeName = "materialRemaining")
    public Integer getMaterialRemaining() {
        return materialRemaining;
    }

    public void setMaterialRemaining(Integer materialRemaining) {
        this.materialRemaining = materialRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filament)) return false;
        Filament filament = (Filament) o;
        return Objects.equals(filamentId, filament.filamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filamentId);
    }
}
