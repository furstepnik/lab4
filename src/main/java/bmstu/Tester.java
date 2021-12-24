package bmstu;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class Tester extends AbstractActor {
    private ActorRef storeActor;
    private final String JS = "nashorn";
    public Tester (ActorRef ac) {
        this.storeActor = ac;
    }

    
}
