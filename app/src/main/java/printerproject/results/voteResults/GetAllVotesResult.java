package printerproject.results.voteResults;

import printerproject.dynamodb.models.Vote;

import java.util.List;

public class GetAllVotesResult {
    private final List<Vote> voteList;

    private GetAllVotesResult(List<Vote> voteList) {
        this.voteList = voteList;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Vote> voteList;

        public Builder withVoteList(List<Vote> voteList) {
            this.voteList = voteList;
            return this;
        }

        public GetAllVotesResult build() {
            return new GetAllVotesResult(voteList);
        }
    }
}
