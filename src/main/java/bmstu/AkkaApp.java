package bmstu;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.routing.Router;
import akka.stream.ActorMaterializer;

public class AkkaApp {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("AkkaApp");
        ActorRef rout = system.actorOf(Props.create(Router.class, system));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = 
    }
}
