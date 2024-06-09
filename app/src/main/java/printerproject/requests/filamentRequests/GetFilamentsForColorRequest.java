package printerproject.requests.filamentRequests;

public class GetFilamentsForColorRequest {
    private final String color;

    private GetFilamentsForColorRequest(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    public static class Builder {
        private String color;

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public GetFilamentsForColorRequest build() { return new GetFilamentsForColorRequest(color); }
    }
}