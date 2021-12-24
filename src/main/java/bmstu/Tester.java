package bmstu;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.Invocable;

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
        Invocable invoc = (Invocable) engine;
        return invoc.invokeFunction(parent.getNameFunction(), test.getParameters()).toString();
    }

    private Test checkResult(Test test) {
        try {
            String getRes = runTest(test);
            test.setActualResult(getRes);
        } catch (Exception excep) {
            test.setActualResult(excep.toString());
        }
        return test;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(Test.class, msg -> storeActor.tell(checkResult(msg), ActorRef.noSender()))
                .build();
    }
}
