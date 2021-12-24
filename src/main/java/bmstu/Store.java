package bmstu;

public class Store {

    private Map<String, ArrayList<Test>> store = new HashMap<>();
    private static final String FAIL = "FAIL";
    private static final Strinc SUCCESS = "SUCCESS";
    private void putTest(Test test) {
        String testId = test.getTest().getPackageId();
        if (this.store.containsKey(pId)) {
            this.store.get(pId).add(test);
        } else {
            ArrayList<Test> arrayTests = new ArrayList<>();
            arrayTests.add(test);
            this.store.put(pId, arrayTests);
        }
    }
}
