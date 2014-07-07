package org.junit.formatter.test;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class AnnotatedTest {

    private static HashMap<String, String> companies = new HashMap<String, String>();
    private static HashMap<String, String> projects = new HashMap<String, String>();

    public static String getCompany(String key) {
        return companies.get(key);
    }

    public static void setCompany(String company) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            try {
                Class<?> clazz = Class.forName(stackTraceElement.getClassName());
                Method method = clazz.getMethod(stackTraceElement.getMethodName());
                if (method.getAnnotation(Test.class) != null) {
                    companies.put(stackTraceElement.getMethodName(), company);
                }
            } catch (NoSuchMethodException e) {
            } catch (ClassNotFoundException e) {
            }
        }
    }

    public static String getProject(String key) {
        return projects.get(key);
    }

    public static void setProject(String project) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            try {
                Class<?> clazz = Class.forName(stackTraceElement.getClassName());
                Method method = clazz.getMethod(stackTraceElement.getMethodName());
                if (method.getAnnotation(Test.class) != null) {
                    projects.put(stackTraceElement.getMethodName(), project);
                }
            } catch (NoSuchMethodException e) {
            } catch (ClassNotFoundException e) {
            }
        }
    }
}
