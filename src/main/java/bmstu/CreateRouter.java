package bmstu;


import scala.concurrent.Future;
import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;

public class CreateRouter extends AllDirectives{
    private final ActorRef router;
    private final String NAME = "packageId";
    private final int TIME_OUT = 3000;

    public CreateRouter(ActorRef r) {
        this.router = r;
    }

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
