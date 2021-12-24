package bmstu;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.routing.Router;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

public class AkkaApp {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("AkkaApp");
        ActorRef rout = system.actorOf(Props.create(Router.class, system));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> flow =
                new CreateRouter(rout).createRoute().flow(system, materializer);
        http.bindAndHandle(flow, ConnectHttp.toHost(HOST, PORT), materializer);
    }
}
