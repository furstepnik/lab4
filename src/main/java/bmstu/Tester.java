package bmstu;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptException;

public class Tester extends AbstractActor {
    private ActorRef storeActor;
    private final String JS = "nashorn";
    public Tester (ActorRef ac) {
        this.storeActor = ac;
    }

    private String runTest(Test test) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(JS);
        TestMessage parent = test.getTest();
        engine.eval(parent.getScript());
        
    }
}
