package printerproject.lambda.filamentLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.filamentRequests.GetFilamentRequest;
import printerproject.results.filamentResults.GetFilamentResult;

public class GetFilamentLambda
        extends LambdaActivityRunner<GetFilamentRequest, GetFilamentResult>
        implements RequestHandler<LambdaRequest<GetFilamentRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetFilamentRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetFilamentRequest.builder()
                                .withFilamentId(path.get("filamentId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetFilamentActivity().handleRequest(request)
        );
    }
}
