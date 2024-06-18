package printerproject.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.requests.modelRequests.CreateModelRequest;
import printerproject.results.modelResults.CreateModelResult;

public class CreateModelLambda
        extends LambdaActivityRunner<CreateModelRequest, CreateModelResult>
        implements RequestHandler<LambdaRequest<CreateModelRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateModelRequest> input, Context context) {
        return super.runActivity(() -> {
                CreateModelRequest unauthenticatedRequest = input.fromBody(CreateModelRequest.class);
                return input.fromPath(path ->
                        CreateModelRequest.builder()
                                .withIsActive(unauthenticatedRequest.getIsActive())
                                .withKeyword(unauthenticatedRequest.getKeyword())
                                .withPreview(unauthenticatedRequest.getPreview())
                                .withMaterialUsed(unauthenticatedRequest.getMaterialUsed())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateModelActivity().handleRequest(request)
        );
    }
}
