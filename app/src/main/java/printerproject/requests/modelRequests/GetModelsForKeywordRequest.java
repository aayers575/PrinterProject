package printerproject.requests.modelRequests;

public class GetModelsForKeywordRequest {
    private final String keyword;

    private GetModelsForKeywordRequest(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    public static class Builder {
        private String keyword;

        public Builder withKeyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public GetModelsForKeywordRequest build() { return new GetModelsForKeywordRequest(keyword); }
    }
}