package printerproject.requests.filamentRequests;

public class DeleteFilamentRequest {
    private final String filamentId;

    private DeleteFilamentRequest(String filamentId) {
        this.filamentId = filamentId;
    }

    public String getFilamentId() {
        return filamentId;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    public static class Builder {
        private String filamentId;

        public Builder withFilamentId(String filamentId) {
            this.filamentId = filamentId;
            return this;
        }

        public DeleteFilamentRequest build() { return new DeleteFilamentRequest(filamentId); }
    }
}
