package printerproject.requests.voteRequests;

public class GetVoteRequest {
    private final String voteId;

    private GetVoteRequest(String voteId) {
        this.voteId = voteId;
    }

    public String getVoteId() {
        return voteId;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String voteId;

        public Builder withVoteId(String voteId) {
            this.voteId = voteId;
            return this;
        }
        public GetVoteRequest build() {
            return new GetVoteRequest(voteId);
        }
    }
}