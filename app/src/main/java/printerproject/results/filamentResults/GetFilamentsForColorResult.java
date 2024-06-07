package printerproject.results.filamentResults;

import printerproject.dynamodb.models.Model;

import java.util.List;

public class GetModelsForKeywordResult {
    private final List<Model> models;

    private GetModelsForKeywordResult(List<Model> models) { this.models = models; }

    public List<Model> getModels() {
        return models;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private List<Model> models;

        public Builder withModelList(List<Model> models) {
            this.models = models;
            return this;
        }

        public GetModelsForKeywordResult build() { return new GetModelsForKeywordResult(models); }
    }
}