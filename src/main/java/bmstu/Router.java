package bmstu;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class Router {
    private final ActorRef actor;
    private final ActorRef pool;

    private final int WORK_NUMBER = 5;

    public Router(ActorSystem system) {
        actor = system.actorOf(Props.create(Store.class));
        pool = system.actorOf(new RoundRobinPool(WORK_NUMBER).props(Props.create(TesterActor.class, storeActor)));
    }
}
