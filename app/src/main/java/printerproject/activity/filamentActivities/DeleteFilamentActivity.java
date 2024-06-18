package printerproject.activity.filamentActivities;

import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.requests.filamentRequests.DeleteFilamentRequest;
import printerproject.results.filamentResults.DeleteFilamentResult;

import javax.inject.Inject;

public class DeleteFilamentActivity {
    private final FilamentDao filamentDao;

    /**
     * Instantiates a new DeleteFilamentActivity object.
     *
     * @param filamentDao FilamentDao to access the playlist table.
     */
    @Inject
    public DeleteFilamentActivity(FilamentDao filamentDao) {
        this.filamentDao = filamentDao;
    }

    /**
     * This method handles the incoming request by deleting a filament from the database, if it exists.
     * <p>
     * It then returns the deleted filament.
     *
     * @param deleteFilamentRequest request object containing the orgId and filamentId
     * @return GetFilamentResult result object containing the API defined {@link Filament}
     */

    public DeleteFilamentResult handleRequest(final DeleteFilamentRequest deleteFilamentRequest) {

        Filament filament = new Filament();
        filament.setFilamentId(deleteFilamentRequest.getFilamentId());
        filamentDao.deleteFilament(filament);
        return DeleteFilamentResult.builder()
                .withFilament(filament)
                .build();
    }


}
