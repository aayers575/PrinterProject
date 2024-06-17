package printerproject.requests.voteRequests;

public class DeleteVoteRequest {
    private final String voteId;

    private DeleteVoteRequest(String voteId) {
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
        public DeleteVoteRequest build() {
            return new DeleteVoteRequest(voteId);
        }
    }
}