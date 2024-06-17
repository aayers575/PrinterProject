package printerproject.lambda.filamentLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.filamentRequests.GetFilamentsForColorRequest;
import printerproject.results.filamentResults.GetFilamentsForColorResult;

public class GetFilamentsForColorLambda
        extends LambdaActivityRunner<GetFilamentsForColorRequest, GetFilamentsForColorResult>
        implements RequestHandler<LambdaRequest<GetFilamentsForColorRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetFilamentsForColorRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetFilamentsForColorRequest.builder()
                                .withColor(path.get("color"))
                                .withIsActive(path.get("isActive"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetFilamentsForColorActivity().handleRequest(request)
        );
    }
}