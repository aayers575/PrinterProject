package printerproject.requests.modelRequests;

public class DeleteModelRequest {
    private final String modelId;

    private DeleteModelRequest(String modelId) {
        this.modelId = modelId;
    }

    public String getModelId() {
        return modelId;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    public static class Builder {
        private String modelId;

        public Builder withModelId(String modelId) {
            this.modelId = modelId;
            return this;
        }

        public DeleteModelRequest build() { return new DeleteModelRequest(modelId); }
    }
}
