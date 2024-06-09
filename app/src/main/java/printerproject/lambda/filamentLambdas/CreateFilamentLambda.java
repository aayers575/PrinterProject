package printerproject.lambda.filamentLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.filamentRequests.CreateFilamentRequest;
import printerproject.results.filamentResults.CreateFilamentResult;

public class CreateFilamentLambda
        extends LambdaActivityRunner<CreateFilamentRequest, CreateFilamentResult>
        implements RequestHandler<LambdaRequest<CreateFilamentRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateFilamentRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateFilamentRequest unauthenticatedRequest = input.fromBody(CreateFilamentRequest.class);
                    return input.fromPath(path ->
                            CreateFilamentRequest.builder()
                                    .withIsActive(unauthenticatedRequest.getIsActive())
                                    .withColor(unauthenticatedRequest.getColor())
                                    .withMaterial(unauthenticatedRequest.getMaterial())
                                    .withMaterialRemaining(unauthenticatedRequest.getMaterialRemaining())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateFilamentActivity().handleRequest(request)
        );
    }
}