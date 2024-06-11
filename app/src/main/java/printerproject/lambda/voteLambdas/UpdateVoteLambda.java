package printerproject.lambda.voteLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.voteRequests.UpdateVoteRequest;
import printerproject.results.voteResults.UpdateVoteResult;

public class UpdateVoteLambda
        extends LambdaActivityRunner<UpdateVoteRequest, UpdateVoteResult>
        implements RequestHandler<LambdaRequest<UpdateVoteRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateVoteRequest> input, Context context) {
        return super.runActivity(
                () ->
                        input.fromPath(path ->
                                UpdateVoteRequest.builder()
                                        .withVoteId(path.get("voteId"))
                                        .withIsActive(input.fromBody(UpdateVoteRequest.class).getIsActive())
                                        .withVotesByKeyword(input.fromBody(UpdateVoteRequest.class).getVotesByKeyword())

                                        .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateVoteActivity().handleRequest(request)
        );
    }
}
