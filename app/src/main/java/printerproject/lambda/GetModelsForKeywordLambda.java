package printerproject.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.requests.modelRequests.GetModelsForKeywordRequest;
import printerproject.results.modelResults.GetModelsForKeywordResult;

public class GetModelsForKeywordLambda
        extends LambdaActivityRunner<GetModelsForKeywordRequest, GetModelsForKeywordResult>
        implements RequestHandler<LambdaRequest<GetModelsForKeywordRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetModelsForKeywordRequest> input, Context context) {
        return super.runActivity(() -> input.fromPath(path ->
                GetModelsForKeywordRequest.builder()
                        .withKeyword(path.get("keyword"))
                        .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetModelsForKeywordActivity().handleRequest(request)
        );
    }
}
