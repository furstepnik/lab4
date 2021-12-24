package bmstu;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;

public class Router extends AbstractActor {
    private final ActorRef actor;
    private final ActorRef pool;

    private final int WORK_NUMBER = 5;

    public Router(ActorSystem system) {
        actor = system.actorOf(Props.create(Store.class));
        pool = system.actorOf(new RoundRobinPool(WORK_NUMBER).props(Props.create(Tester.class, storeActor)));
    }

    private void runTests(TestMessage test) {
        for (Test t : test.getTests()) {
            t.setTest(test);
            pool.tell(t, ActorRef.noSender());
        }
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(TestMessage.class, msg -> runTests(msg))
                .match(String.class, msg -> storeActor.forward(msg, getContex()))
                .build();
    }
}
