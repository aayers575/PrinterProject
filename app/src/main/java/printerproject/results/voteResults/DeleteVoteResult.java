package printerproject.results.voteResults;

import printerproject.dynamodb.models.Vote;

public class DeleteVoteResult {

    private final Vote vote;

    private DeleteVoteResult(Vote vote) {
        this.vote = vote;
    }

    public Vote getVote() { return vote; }

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
        public DeleteVoteResult build() {
            return new DeleteVoteResult(vote);
        }
    }
}