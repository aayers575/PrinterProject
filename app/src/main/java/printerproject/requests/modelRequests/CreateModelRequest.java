package printerproject.requests.modelRequests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateModelRequest.Builder.class)
public class CreateModelRequest {
    private String modelId;
    private String isActive;
    private String keyword;
    private byte[] preview;
    private Integer materialUsed;


    private CreateModelRequest(String isActive, String keyword, byte[] preview, Integer materialUsed) {
        this.isActive = isActive;
        this.keyword = keyword;
        this.preview = preview;
        this.materialUsed = materialUsed;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getKeyword() { return keyword; }

    public byte[] getPreview() { return preview; }

    public Integer getMaterialUsed() { return materialUsed; }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {
        private String isActive;
        private String keyword;
        private byte[] preview;
        private Integer materialUsed;

        public Builder withIsActive(String isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder withKeyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public Builder withPreview(byte[] preview) {
            this.preview = preview;
            return this;
        }

        public Builder withMaterialUsed(Integer materialUsed) {
            this.materialUsed = materialUsed;
            return  this;
        }

        public CreateModelRequest build() { return new CreateModelRequest(isActive, keyword, preview, materialUsed); }
    }
}
