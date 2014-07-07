import org.junit.Ignore;
import org.junit.Test;
import org.junit.formatter.annotations.Company;
import org.junit.formatter.test.AnnotatedTest;
import org.junit.formatter.JUnitFormatter;
import org.junit.formatter.annotations.Project;
import org.junit.runner.JUnitCore;

import static org.junit.Assert.assertTrue;

public class FormatterTest extends AnnotatedTest {

    // Run JUnit tests from Eclipse/IDEA with JUnitFormatter
    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new JUnitFormatter());
        core.run(FormatterTest.class);
    }

    @Test
    @Company("company1")
    @Project("project1")
    public void testPassed() {
    }

    @Test
    @Company("company2")
    @Project("project2")
    public void testFailed() {
        assertTrue("Error message", false);
    }

    @Test
    public void testDynamicAttributes() {
        setCompany("company3");
        setProject("project3");
    }

    @Test
    @Ignore
    public void testIgnored() {
    }
}
