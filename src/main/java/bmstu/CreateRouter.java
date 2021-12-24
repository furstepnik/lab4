package bmstu;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;

public class CreateRouter {
    private final ActorRef router;
    private final String NAME = "packageId";
    private final int TIME_OUT = 3000;

    public Route createRoute() {
        return concat(
                get(() ->
                    parameter(NAME, id -> {
                        Future<Object> res = Patterns.ask(router, id, TIME_OUT);
                        return completeOKWithFuture(res, Jackson.marshaller());
                })),
                post(() ->
                        entity(
                                Jackson.unmarshaller(TestMessage.class), testMsg -> {
                                    router.tell(testMsg, ActorRef.noSender());
                                    return complete("OK");
                                }
                        ))
        );
    }
}
