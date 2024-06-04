package printerproject.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.requests.modelRequests.DeleteModelRequest;
import printerproject.results.modelResults.DeleteModelResult;

public class DeleteModelLambda
        extends LambdaActivityRunner<DeleteModelRequest, DeleteModelResult>
        implements RequestHandler<LambdaRequest<DeleteModelRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteModelRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteModelRequest.builder()
                                .withModelId(path.get("modelId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteModelActivity().handleRequest(request)
        );
    }
}