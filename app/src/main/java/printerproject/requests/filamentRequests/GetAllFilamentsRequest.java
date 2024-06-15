package printerproject.requests.filamentRequests;

public class GetAllFilamentsRequest {

    private GetAllFilamentsRequest() {

    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public GetAllFilamentsRequest build() {
            return new GetAllFilamentsRequest();
        }
    }
}
