package printerproject.results.voteResults;

import printerproject.dynamodb.models.Vote;

public class UpdateVoteResult {
    private final Vote vote;

    private UpdateVoteResult(Vote vote) {
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

        public UpdateVoteResult build() {
            return new UpdateVoteResult(vote);
        }
    }
}