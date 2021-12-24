package bmstu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {
    private String testName;
    private String expectedResult;
    private String actualResult;
    private TestMessage test;
    private Object[] parameters;
    private static final String NAME_TEST = "teatName";
    private static final String EXPECTED_RESULT = "expectedResult";
    private static final String PARAMETERS = "params";

    @JsonCreator
    public Test(
            @JsonProperty(NAME_TEST) String nameTest,
            @JsonProperty(EXPECTED_RESULT) String res,
            @JsonProperty(PARAMETERS) Integer[] p
    ) {
        this.testName = nameTest;
        this.expectedResult = res;
        this.parameters = p;
        this.actualResult = "";
    }

    public String getTestName() {
        return this.testName;
    }

    public String getExpectedResult() {
        return this.expectedResult;
    }

    public Object[] getParameters () {
        return this.parameters;
    }

    public TestMessage getTest() {
        return this.test;
    }

    public String getActualResult() {
        return this.actualResult;
    }

    public void setTest(TestMessage p) {
        this.test = p;
    }

    public void setActualResult(String r) {
        this.actualResult = r;
    }
}
