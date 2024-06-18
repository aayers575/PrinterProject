package printerproject.requests.filamentRequests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateFilamentRequest.Builder.class)
public class UpdateFilamentRequest {
    private String filamentId;
    private String isActive;
    private String color;
    private String material;
    private Integer materialRemaining;
    

    private UpdateFilamentRequest(String filamentId, String isActive, String color,
                                  String material, Integer materialRemaining) {
        this.filamentId = filamentId;
        this.isActive = isActive;
        this.color = color;
        this.material = material;
        this.materialRemaining = materialRemaining;
    }

    public String getFilamentId() {
        return filamentId;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    public Integer getMaterialRemaining() {
        return materialRemaining;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String filamentId;
        private String isActive;
        private String color;
        private String material;
        private Integer materialRemaining;

        public Builder withFilamentId(String filamentId) {
            this.filamentId = filamentId;
            return this;
        }

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

        public UpdateFilamentRequest build() { return new UpdateFilamentRequest(filamentId, isActive, color, material, materialRemaining); }
    }
}
