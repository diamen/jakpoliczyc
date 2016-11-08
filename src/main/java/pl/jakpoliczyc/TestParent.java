package pl.jakpoliczyc;

import org.springframework.beans.factory.annotation.Autowired;

public class TestParent {

    private TestChild testChild;

    @Autowired
    public TestParent(TestChild testChild) {
        this.testChild = testChild;
    }

    public void run() {
        testChild.run();
    }
}
