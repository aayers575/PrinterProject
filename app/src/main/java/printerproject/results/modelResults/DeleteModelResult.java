package printerproject.results.modelResults;

import printerproject.dynamodb.models.Model;

public class DeleteModelResult {
    private final Model model;

    private DeleteModelResult(Model model) {
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

        public DeleteModelResult build() { return new DeleteModelResult(model); }
    }
}
