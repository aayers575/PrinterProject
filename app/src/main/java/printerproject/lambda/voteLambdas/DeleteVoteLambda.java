package printerproject.lambda.voteLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.voteRequests.DeleteVoteRequest;
import printerproject.results.voteResults.DeleteVoteResult;

public class DeleteVoteLambda
        extends LambdaActivityRunner<DeleteVoteRequest, DeleteVoteResult>
        implements RequestHandler<LambdaRequest<DeleteVoteRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteVoteRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteVoteRequest.builder()
                                .withVoteId(path.get("voteId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteVoteActivity().handleRequest(request)
        );
    }
}