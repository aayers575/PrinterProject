package printerproject.results.modelResults;

import printerproject.dynamodb.models.Model;

public class CreateModelResult {
    private final Model model;

    private CreateModelResult(Model model) {
        this.model = model;
    }

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

        public CreateModelResult build() { return new CreateModelResult(model); }
    }
}
