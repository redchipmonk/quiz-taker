import java.io.*;
import java.util.Scanner;
/**
 * This class tests the operations of SurveyTree.java by comparing the expected and observed results.
 */
public class TestSurveyTree {
    public static void main(String[] args) throws FileNotFoundException {
        File inFile = new File("./colors-cereals.txt");
        SurveyTree survey = new SurveyTree(new Scanner(inFile));
        check(survey.root);
        survey.replaceResult("Froot Loops", "gold", "silver", "Cheerios",
                "Frosted Mini-Wheats");
        check2(survey.root);
    }

    /**
     * First test checks that the binary tree is in the right order
     * @param node root node to check
     */
    static void check(SurveyTree.SurveyTreeNode node) {
        check(node.value, "red/blue", "Test reading data.");
        check(node.left.value, "yellow/green", "Test reading data.");
        check(node.right.value, "purple/orange", "Test reading data.");
        check(node.left.left.value, "Froot Loops", "Test reading data.");
        check(node.left.right.value, "Raisin Bran", "Test reading data.");
        check(node.right.left.value, "Frosted Flakes", "Test reading data.");
        check(node.right.right.value, "black/white", "Test reading data.");
        check(node.right.right.left.value, "Rice Krispies", "Test reading data.");
        check(node.right.right.right.value, "Fruity Pebbles", "Test reading data.");
    }

    /**
     * Second test checks if replaceResult method works
     * @param node root node to check
     */
    static void check2(SurveyTree.SurveyTreeNode node) {
        check(node.value, "red/blue", "Test reading data.");
        check(node.left.value, "yellow/green", "Test reading data.");
        check(node.right.value, "purple/orange", "Test reading data.");
        check(node.left.left.value, "gold/silver", "Test reading data.");
        check(node.left.left.left.value, "Cheerios", "Test reading data.");
        check(node.left.left.right.value, "Frosted Mini-Wheats", "Test reading data.");
                check(node.left.right.value, "Raisin Bran", "Test reading data.");
        check(node.right.left.value, "Frosted Flakes", "Test reading data.");
        check(node.right.right.value, "black/white", "Test reading data.");
        check(node.right.right.left.value, "Rice Krispies", "Test reading data.");
        check(node.right.right.right.value, "Fruity Pebbles", "Test reading data.");
    }

    /**
     * Compares expected and observed result
     * @param result String observed result
     * @param expected result
     * @param descr description of test
     */
    static void check(String result, String expected, String descr) {
        if (!result.equals(expected))
            System.out.println("FAILED " + descr + " expected " + expected + " but found " + result);
    }
}