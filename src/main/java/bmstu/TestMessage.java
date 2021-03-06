package bmstu;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMessage implements Serializable{
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
            @JsonProperty(PACKAGE_ID) String pId,
            @JsonProperty(SCRIPT) String sc,
            @JsonProperty(FUNCTION_NAME) String fn,
            @JsonProperty(TESTS) List<Test> t
    ) {
        this.packageId = pId;
        this.nameFunction = fn;
        this.tests = t;
        this.script = sc;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public String getScript() {
        return this.script;
    }

    public String getNameFunction() {
        return this.nameFunction;
    }

    public List<Test> getTests() {
        return this.tests;
    }
}
