package printerproject.lambda.filamentLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.filamentRequests.GetAllFilamentsRequest;
import printerproject.results.filamentResults.GetAllFilamentsResult;

public class GetAllFilamentsLambda
        extends LambdaActivityRunner<GetAllFilamentsRequest, GetAllFilamentsResult>
        implements RequestHandler<LambdaRequest<GetAllFilamentsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllFilamentsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetAllFilamentsRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllFilamentsActivity().handleRequest(request)
        );
    }
}