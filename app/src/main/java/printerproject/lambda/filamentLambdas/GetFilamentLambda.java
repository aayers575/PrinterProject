package printerproject.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.requests.modelRequests.GetModelRequest;
import printerproject.results.modelResults.GetModelResult;

public class GetModelLambda
        extends LambdaActivityRunner<GetModelRequest, GetModelResult>
        implements RequestHandler<LambdaRequest<GetModelRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetModelRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetModelRequest.builder()
                                .withModelId(path.get("modelId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetModelActivity().handleRequest(request)
        );
    }
}
