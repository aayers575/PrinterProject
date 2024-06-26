package printerproject.results.filamentResults;

import printerproject.dynamodb.models.Filament;

public class GetFilamentResult {
    private final Filament filament;

    private GetFilamentResult(Filament filament) {
        this.filament = filament;
    }

    public Filament getFilament() {
        return filament;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Filament filament;

        public Builder withFilament(Filament filament) {
            this.filament = filament;
            return this;
        }

        public GetFilamentResult build() { return new GetFilamentResult(filament); }
    }
}
