package printerproject.results.modelResults;

import printerproject.dynamodb.models.Model;

public class UpdateModelResult {
    private final Model model;

    private UpdateModelResult(Model model) { this.model = model; }

    public Model getModel() {
        return model;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Model model;

        public Builder withModel(Model model) {
            this.model = model;
            return this;
        }

        public UpdateModelResult build() { return new UpdateModelResult(model); }
    }
}