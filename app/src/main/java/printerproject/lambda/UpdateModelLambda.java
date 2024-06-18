package printerproject.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.requests.modelRequests.UpdateModelRequest;
import printerproject.results.modelResults.UpdateModelResult;

public class UpdateModelLambda
        extends LambdaActivityRunner<UpdateModelRequest, UpdateModelResult>
        implements RequestHandler<LambdaRequest<UpdateModelRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateModelRequest> input, Context context) {
        return super.runActivity(() -> {
            UpdateModelRequest unauthenticatedRequest = input.fromBody(UpdateModelRequest.class);
            return input.fromPath(path ->
                    UpdateModelRequest.builder()
                            .withModelId(path.get("modelId"))
                            .withIsActive(unauthenticatedRequest.getIsActive())
                            .withKeyword(unauthenticatedRequest.getKeyword())
                            .withPreview(unauthenticatedRequest.getPreview())
                            .withMaterialUsed(unauthenticatedRequest.getMaterialUsed())
                            .build());
            },
            (request, serviceComponent) ->
                        serviceComponent.provideUpdateModelActivity().handleRequest(request)
        );
    }
}
