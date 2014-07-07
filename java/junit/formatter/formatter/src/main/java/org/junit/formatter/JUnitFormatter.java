package org.junit.formatter;

import org.junit.formatter.annotations.Company;
import org.junit.formatter.annotations.Project;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.String.format;
import static java.lang.System.getProperty;

/**
 * Prints XML output in JUnit-compatible format.
 * Custom attributes could be added as annotations to test methods.
 * XML report filename could be set using ${junit.report} property.
 */
public class JUnitFormatter extends RunListener {

    private TestSuite testSuite = new TestSuite();
    private TestCase testCase;
    private long time;

    private void updateAttributes(TestCase testCase, Description description) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Company company = description.getAnnotation(Company.class);
        if (company != null) {
            testCase.setCompany(company.value());
        } else {
            Method getCompany = description.getTestClass().getMethod("getCompany", String.class);
            Object object = getCompany.invoke(null, description.getMethodName());
            if (object != null) {
                testCase.setCompany(object.toString());
            }
        }
        Project project = description.getAnnotation(Project.class);
        if (project != null) {
            testCase.setProject(project.value());
        } else {
            Method getProject = description.getTestClass().getMethod("getProject", String.class);
            Object object = getProject.invoke(null, description.getMethodName());
            if (object != null) {
                testCase.setProject(object.toString());
            }
        }
    }

    @Override
    public void testRunFinished(Result result) {
        try {
            File file = new File(getProperty("junit.report", "junit.xml"));
            if (file.exists()) file.delete();
            JAXBContext jaxbContext = JAXBContext.newInstance(TestSuite.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(testSuite, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testRunStarted(Description description) throws Exception {
        time = new Date().getTime();
    }

    @Override
    public void testStarted(Description description) throws Exception {
        testCase = new TestCase();
        testCase.setClassName(description.getClassName());
        testCase.setName(description.getMethodName());
    }

    @Override
    public void testFinished(Description description) throws Exception {
        testCase.setTime(new Date().getTime() - time);
        testSuite.add(testCase);
        updateAttributes(testCase, description);
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        testCase.setFailure(new TestCase.Failure(failure.getMessage(), failure.getTrace()));
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        testCase = new TestCase();
        testCase.setClassName(description.getClassName());
        testCase.setName(description.getMethodName());
        testCase.setSkipped(new TestCase.Skipped());
        testSuite.add(testCase);
        updateAttributes(testCase, description);
    }

    @XmlRootElement(name = "testsuite")
    public static class TestSuite {

        @XmlElement(name = "testcase")
        ArrayList<TestCase> testCases = new ArrayList<TestCase>();

        public void add(TestCase testCase) {
            testCases.add(testCase);
        }

        @XmlAttribute(name = "tests")
        public int getTestCasesCount() {
            return testCases.size();
        }

        @XmlAttribute(name = "failures")
        public int getFailedTestCasesCount() {
            int i = 0;
            for (TestCase testCase : testCases) {
                if (testCase.getFailure() != null) {
                    i++;
                }
            }
            return i;    }

        @XmlAttribute(name = "skipped")
        public int getSkippedTestCasesCount() {
            int i = 0;
            for (TestCase testCase : testCases) {
                if (testCase.getSkipped() != null) {
                    i++;
                }
            }
            return i;
        }
    }

    public static class TestCase {

        private String className;
        private String name;
        private String company;
        private String project;
        private long time;
        private Failure failure;
        private Skipped skipped;

        @XmlAttribute(name = "classname")
        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        @XmlAttribute(name = "name")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlAttribute(name = "time")
        public String getTime() {
            return format("%.3f", (double) time / 1000).replace(',', '.');
        }

        public void setTime(long time) {
            this.time = time;
        }

        @XmlElement
        public Failure getFailure() {
            return failure;
        }

        public void setFailure(Failure failure) {
            this.failure = failure;
        }

        @XmlElement
        public Skipped getSkipped() {
            return skipped;
        }

        public void setSkipped(Skipped skipped) {
            this.skipped = skipped;
        }

        @XmlAttribute
        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        @XmlAttribute
        public String getProject() {
            return project;
        }


        public void setProject(String project) {
            this.project = project;
        }

        public static class Failure {

            private String message;
            private String trace;

            public Failure(String message, String trace) {
                this.message = message;
                this.trace = trace;
            }

            @XmlAttribute
            public String getMessage() {
                return message;
            }

            @XmlValue
            public String getTrace() {
                return trace;
            }
        }

        public static class Skipped {
        }
    }
}
