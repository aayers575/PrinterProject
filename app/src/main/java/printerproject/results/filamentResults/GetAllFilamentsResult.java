package printerproject.results.filamentResults;

import printerproject.dynamodb.models.Filament;

import java.util.List;

public class GetAllFilamentsResult {
    private final List<Filament> filamentList;

    private GetAllFilamentsResult(List<Filament> filamentList) {
        this.filamentList = filamentList;
    }

    public List<Filament> getFilamentList() {
        return filamentList;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Filament> filamentList;

        public Builder withFilamentList(List<Filament> filamentList) {
            this.filamentList = filamentList;
            return this;
        }

        public GetAllFilamentsResult build() {
            return new GetAllFilamentsResult(filamentList);
        }
    }
}
