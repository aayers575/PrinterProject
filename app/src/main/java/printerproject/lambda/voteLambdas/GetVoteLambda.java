package printerproject.lambda.voteLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.voteRequests.GetVoteRequest;
import printerproject.results.voteResults.GetVoteResult;

public class GetVoteLambda
        extends LambdaActivityRunner<GetVoteRequest, GetVoteResult>
        implements RequestHandler<LambdaRequest<GetVoteRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetVoteRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetVoteRequest.builder()
                                .withVoteId(path.get("voteId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetVoteActivity().handleRequest(request)
        );
    }
}