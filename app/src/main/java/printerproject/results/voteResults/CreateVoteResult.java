package printerproject.results.voteResults;

import printerproject.dynamodb.models.Vote;

public class CreateVoteResult {
    private final Vote vote;

    private CreateVoteResult(Vote vote) {
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

        public CreateVoteResult build() {
            return new CreateVoteResult(vote);
        }
    }
}