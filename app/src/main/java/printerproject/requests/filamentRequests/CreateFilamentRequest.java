package printerproject.requests.filamentRequests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateFilamentRequest.Builder.class)
public class CreateFilamentRequest {
    private String isActive;
    private String color;
    private String material;
    private Integer materialRemaining;


    private CreateFilamentRequest(String isActive, String color, String material, Integer materialRemaining) {
        this.isActive = isActive;
        this.color = color;
        this.material = material;
        this.materialRemaining = materialRemaining;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getColor() { return color; }

    public String getMaterial() { return material; }

    public Integer getMaterialRemaining() { return materialRemaining; }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {
        private String isActive;
        private String color;
        private String material;
        private Integer materialRemaining;

        public Builder withIsActive(String isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withMaterial(String material) {
            this.material = material;
            return this;
        }

        public Builder withMaterialRemaining(Integer materialRemaining) {
            this.materialRemaining = materialRemaining;
            return  this;
        }

        public CreateFilamentRequest build() { return new CreateFilamentRequest(isActive, color, material, materialRemaining); }
    }
}
