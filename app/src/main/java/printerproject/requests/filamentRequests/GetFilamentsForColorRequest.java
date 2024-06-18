package printerproject.requests.filamentRequests;

public class GetFilamentsForColorRequest {
    private final String color;
    private final String isActive;

    private GetFilamentsForColorRequest(String color, String isActive) {
        this.color = color;
        this.isActive = isActive;
    }

    public String getColor() {
        return color;
    }
    public String getIsActive() {
        return isActive;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    public static class Builder {
        private String color;
        private String isActive;

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withIsActive(String isActive) {
            this.isActive = isActive;
            return this;
        }

        public GetFilamentsForColorRequest build() { return new GetFilamentsForColorRequest(color, isActive); }
    }
}
