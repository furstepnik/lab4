package bmstu;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Store extends AbstractActor{

    private Map<String, ArrayList<Test>> store = new HashMap<>();
    private static final String FAIL = "FAIL";
    private static final Strinc SUCCESS = "SUCCESS";
    private void putTest(Test test) {
        String testId = test.getTest().getPackageId();
        if (this.store.containsKey(testId)) {
            this.store.get(testId).add(test);
        } else {
            ArrayList<Test> arrayTests = new ArrayList<>();
            arrayTests.add(test);
            this.store.put(testId, arrayTests);
        }
    }

    private Map<String, String> prepareRes(String testId) {
        Map<String,String> mapTestsResult = new HashMap<>();
        if (this.store.containsKey(testId)) {
            for (Test test : this.store.get(testId)) {
                if (test.getActualResult().equals(test.getExpectedResult())) {
                    mapTestResult.put(test.getTestName(), SUCCESS);
                } else {
                    mapTestsResult.put(test.getTestName(), FAIL);
                }
            }
        }
        return mapTestsResult;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(Test.class, t -> putTest(t))
                
    }
}
