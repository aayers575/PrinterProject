package printerproject.activity.filamentActivities;

import printerproject.dynamodb.FilamentDao;
import printerproject.dynamodb.models.Filament;
import printerproject.requests.filamentRequests.GetFilamentsForColorRequest;
import printerproject.results.filamentResults.GetFilamentsForColorResult;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetFilamentsForColorActivity for the Filament endpoint.
 *
 * This API allows the customer to interact with Filament objects in the database.
 */
public class GetFilamentForColorActivity {
    private final FilamentDao filamentDao;

    /**
     * Instantiates a new GetFilamentsForColorActivity object.
     *
     * @param filamentDao FilamentDao to access the playlist table.
     */
    @Inject
    public GetFilamentForColorActivity(FilamentDao filamentDao) { this.filamentDao = filamentDao; }

    /**
     * This method handles the incoming request by retrieving list of filaments belong to an color from the database, if any exist.
     * <p>
     * It then returns the list.
     * <p>
     * If no filaments are found, the method will return an empty list.
     *
     * @param getFilamentsForColorRequest request object containing the color
     * @return GetFilamentsForColorResult result object containing the API defined {@link List<Filament>}
     */

    public GetFilamentsForColorResult handleRequest(final GetFilamentsForColorRequest getFilamentsForColorRequest) {
        List<Filament> filamentList = new ArrayList<>();
        filamentList = filamentDao.loadFilamentsForColor(getFilamentsForColorRequest.getColor());
        return GetFilamentsForColorResult.builder()
                .withFilamentList(filamentList)
                .build();
    }


}