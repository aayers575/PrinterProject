package printerproject.requests.modelRequests;

public class GetModelRequest {
    private final String modelId;

    private GetModelRequest(String modelId) {
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

        public GetModelRequest build() { return new GetModelRequest(modelId); }
    }
}
