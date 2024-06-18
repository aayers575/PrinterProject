package printerproject.lambda.filamentLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.filamentRequests.UpdateFilamentRequest;
import printerproject.results.filamentResults.UpdateFilamentResult;

public class UpdateFilamentLambda
        extends LambdaActivityRunner<UpdateFilamentRequest, UpdateFilamentResult>
        implements RequestHandler<LambdaRequest<UpdateFilamentRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateFilamentRequest> input, Context context) {
        return super.runActivity(() -> {
            UpdateFilamentRequest unauthenticatedRequest = input.fromBody(UpdateFilamentRequest.class);
            return input.fromPath(path ->
                    UpdateFilamentRequest.builder()
                            .withFilamentId(path.get("filamentId"))
                            .withIsActive(unauthenticatedRequest.getIsActive())
                            .withColor(unauthenticatedRequest.getColor())
                            .withMaterial(unauthenticatedRequest.getMaterial())
                            .withMaterialRemaining(unauthenticatedRequest.getMaterialRemaining())
                            .build());
            }, (request, serviceComponent) ->
                serviceComponent.provideUpdateFilamentActivity().handleRequest(request)
        );
    }
}
