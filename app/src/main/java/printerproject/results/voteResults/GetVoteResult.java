package printerproject.results.voteResults;

import printerproject.dynamodb.models.Vote;

public class GetVoteResult {
    private final Vote vote;

    private GetVoteResult(Vote vote) {
        this.vote = vote;
    }

    public Vote getVote() {
        return vote;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Vote vote;

        public Builder withVote(Vote vote) {
            this.vote = vote;
            return this;
        }

        public GetVoteResult build() {
            return new GetVoteResult(vote);
        }
    }
}