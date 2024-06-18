package printerproject.results.filamentResults;

import printerproject.dynamodb.models.Filament;

public class CreateFilamentResult {
    private final Filament filament;

    private CreateFilamentResult(Filament filament) {
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

        public CreateFilamentResult build() { return new CreateFilamentResult(filament); }
    }
}
