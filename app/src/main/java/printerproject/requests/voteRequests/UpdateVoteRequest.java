package printerproject.requests.voteRequests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Map;

@JsonDeserialize(builder = UpdateVoteRequest.Builder.class)
public class UpdateVoteRequest {
    private final String voteId;
    private final String isActive;
    private final Map<String, String> votesByKeyword;

    private UpdateVoteRequest(String voteId, String isActive, Map<String, String> votesByKeyword) {
        this.voteId = voteId;
        this.isActive = isActive;
        this.votesByKeyword = votesByKeyword;
    }

    public String getVoteId() {
        return voteId;
    }

    public String getIsActive() {
        return isActive; }

    public Map<String, String> getVotesByKeyword() {
        return votesByKeyword;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String voteId;
        private String isActive;
        private Map<String, String> votesByKeyword;


        public Builder withVoteId(String voteId) {
            this.voteId = voteId;
            return this;
        }

        public Builder withIsActive(String isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder withVotesByKeyword(Map<String, String> votesByKeyword) {
            this.votesByKeyword = votesByKeyword;
            return this;
        }


        public UpdateVoteRequest build() {
            return new UpdateVoteRequest(voteId, isActive, votesByKeyword);
        }
    }
}