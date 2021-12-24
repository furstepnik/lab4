package bmstu;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TestMessage {
    private String packageId;
    private String nameFunction;
    private List<Test> tests;
    private String script;

    private final String PACKAGE_ID = "packageId";
    private final String FUNCTION_NAME = "functionName";
    private final String SCRIPT = "jsScript";
    private final String TESTS = "tests";

    @JsonCreator
    public TestMessage(
            
    )
}
