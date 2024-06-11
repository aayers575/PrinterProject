package printerproject.lambda.voteLambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import printerproject.lambda.LambdaActivityRunner;
import printerproject.lambda.LambdaRequest;
import printerproject.lambda.LambdaResponse;
import printerproject.requests.voteRequests.CreateVoteRequest;
import printerproject.results.voteResults.CreateVoteResult;

public class CreateVoteLambda
        extends LambdaActivityRunner<CreateVoteRequest, CreateVoteResult>
        implements RequestHandler<LambdaRequest<CreateVoteRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateVoteRequest> input, Context context) {
        return super.runActivity(
                () ->  {
                    return input.fromBody(CreateVoteRequest.class);
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateVoteActivity().handleRequest(request)
        );
    }
}
