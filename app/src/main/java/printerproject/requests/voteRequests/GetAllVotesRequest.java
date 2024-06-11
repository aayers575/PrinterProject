package printerproject.requests.voteRequests;

public class GetAllVotesRequest {

    private GetAllVotesRequest() {

    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public GetAllVotesRequest build() {
            return new GetAllVotesRequest();
        }
    }
}
