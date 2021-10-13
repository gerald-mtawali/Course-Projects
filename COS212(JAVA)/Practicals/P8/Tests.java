/**
 * ========================================================================
 * Testing Framework
 * -----------------
 * You may modify the code as required. This file will be ignored by Fitchfork.
 * 
 * For more information on writing unit tests or how to use this framework, please
 * send an email to the person responsible as indicated on the COS 212 CS page.
 */

/** Extract from Prac 4:
 * A note on testing:
 * 
 * This Main takes a different approach to testing. Previously we printed
 * information to STDOUT and verified the results manually.
 * 
 * This Main uses a very simple testing framework, implemented in the Main class
 * itself. It provides a structure for testing and validates the results at run
 * time.
 * 
 * This provides the following advantages:
 * 
 * - Regression testing - Often as you try to fix one bug, you introduce another
 * bug somewhere else. Using this Main (or a proper unit testing framework such
 * as TestNG) you can be sure that old functions still work as intended after
 * refactoring.
 * 
 * - Quick and easy validation - The framework will give a summary, indicating
 * if all test passed. This saves time and effort as validation is done by the
 * framework and not by a fallible human eye (of course, the tests also need to
 * be correct, otherwise you will be implementing the wrong functionality
 * correctly).
 * 
 * - Understanding of specs and requirements - Your understanding will be tested
 * by writing the test cases first. It it much easier to fix the test cases and
 * a tree on pen and paper, than it is to fix the tree implementation.
 * 
 * You may use the provided framework (can be modified to suit your needs) or
 * your own way of testing. This is just an example of an alternative way of
 * testing.
 */
public class Tests {
     
    public static Integer testCounter = 0;
    public static Integer testsPassed = 0;
    public static String currentTestCaseName = "";

    /**
     * Start a new test case. All future asserts will now be part of this case.
     */
    public static void startTestCase(String name) {
        currentTestCaseName = name;
        testCounter = 0;
        testsPassed = 0;

        System.out.println("=========================");
        System.out.println("CASE: " + currentTestCaseName);
        System.out.println("_________________________");
    }

    /**
     * End the current test case and print a summary
     */
    public static void endTestCase() {
        System.out.println("_________________________");
        System.out.print("SUMMARY: ");
        if (testCounter == testsPassed) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
        System.out.println("\tPassed " + testsPassed + "/" + testCounter + " assertions");

        testCounter = 0;
        testsPassed = 0;
        currentTestCaseName = "";
    }

    /**
     * Asserts that the "actual" object is equal to the "expected" object NOTE: Does
     * not work for asserting null due to .toString()
     */
    public static void assertEquals(Object actual, Object expected, String caseName) {
        int tracer = Thread.currentThread().getStackTrace()[2].getLineNumber();
        testCounter++;

        if (!actual.equals(expected)) {
            System.out.println("(Line " + tracer + ") FAIL: Assert " + testCounter + " - " + caseName);
            System.out.println("\tExpected " + expected.toString() + "\n\tReceieved " + actual.toString());
            System.out.println("\nAborting due to failed test");
            System.exit(1);
        } else {
            System.out.println("(Line " + tracer + ") PASS: Assert " + testCounter + " - " + caseName);
            testsPassed++;

        }
    }

    /**
     * Test if two arrays contain the same objects where order does not matter.
     * Return true if they contain the same elements, false otherwise.
     */
    public static Boolean arrayEquals(Object[] expected, Object[] actual) {
        if (expected.length != actual.length) {
            return false;
        }

        for (int expectedIndex = 0; expectedIndex < expected.length; expectedIndex++) {
            // Find each object from expected
            Boolean found = false;

            for (int actualIndex = 0; actualIndex < actual.length; actualIndex++) {
                if (expected[expectedIndex].equals(actual[actualIndex])) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        // All objects must have been found, or arrays are empty
        return true;
    }
}
