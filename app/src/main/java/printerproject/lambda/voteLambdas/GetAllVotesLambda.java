package printerproject.lambda.voteLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.voteRequests.GetAllVotesRequest;
import printerproject.results.voteResults.GetAllVotesResult;

public class GetAllVotesLambda
        extends LambdaActivityRunner<GetAllVotesRequest, GetAllVotesResult>
        implements RequestHandler<LambdaRequest<GetAllVotesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllVotesRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetAllVotesRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllVotesActivity().handleRequest(request)
        );
    }
}