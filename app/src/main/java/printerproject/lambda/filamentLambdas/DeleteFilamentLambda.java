package printerproject.lambda.filamentLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.filamentRequests.DeleteFilamentRequest;
import printerproject.results.filamentResults.DeleteFilamentResult;

public class DeleteFilamentLambda
        extends LambdaActivityRunner<DeleteFilamentRequest, DeleteFilamentResult>
        implements RequestHandler<LambdaRequest<DeleteFilamentRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteFilamentRequest> input, Context context) {
        return super.runActivity(() -> input.fromPath(path ->
                DeleteFilamentRequest.builder()
                        .withFilamentId(path.get("filamentId"))
                        .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideDeleteFilamentActivity().handleRequest(request)
        );
    }
}
