package printerproject.results.filamentResults;

import printerproject.dynamodb.models.Filament;

import java.util.List;

public class GetFilamentsForColorResult {
    private final List<Filament> filaments;

    private GetFilamentsForColorResult(List<Filament> filaments) { this.filaments = filaments; }

    public List<Filament> getFilaments() {
        return filaments;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private List<Filament> filaments;

        public Builder withFilamentList(List<Filament> filaments) {
            this.filaments = filaments;
            return this;
        }

        public GetFilamentsForColorResult build() { return new GetFilamentsForColorResult(filaments); }
    }
}