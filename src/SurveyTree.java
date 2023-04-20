import java.util.Scanner;
/**
 * This class represents a binary tree containing the questions and answers to a survey. The questions or branches lead
 * to the answers which are the leaves. The questions store a value which is the combination of the left and right
 * child. The answers have a prefix of "END:" which indicates a leaf, a node with no left or right child. The class
 * also has an inner binary tree node class.
 *
 * @author Alvin Le
 */
public class SurveyTree {
    SurveyTreeNode root;
    String title;
    /**
     * Constructor that initializes the root instance variable using the read method
     * @param inputFile Scanner instance to read from file
     */
    public SurveyTree(Scanner inputFile) {
        title = inputFile.nextLine();
        root = read(inputFile, root);
    }
    /**
     * Method that initializes the root instance variable by scanning each line from the file and constructing binary
     * tree
     * @param input Scanner object to read from file
     * @param node SurveyTreeNode to initialize
     * @return SurveyTreeNode that represents binary tree
     */
    private SurveyTreeNode read(Scanner input, SurveyTreeNode node) {
        if (!input.hasNext()) return node;
        String line = input.nextLine();
        node = new SurveyTreeNode(line);
        //if leaf
        if (line.startsWith("END:")) return node;
        else {
            node.left = read(input, node.left);
            node.right = read(input, node.right);
        }
        return node;
    }
    /**
     * Getter method for title of survey
     * @return String title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Helper method of takeSurvey
     * @param console Scanner to read user input
     */
    public void takeSurvey(Scanner console) {
        takeSurvey(console, root);
    }
    /**
     * Method to allow the user to take the survey by inputs in the console
     * @param console Scanner to read user input
     * @param node SurveyTreeNode that represent binary tree survey
     */
    private void takeSurvey(Scanner console, SurveyTreeNode node) {
        if (node.isLeaf()) {
            System.out.println("Your result is: " + node.value);
            return;
        }
        //splits node to prompt user
        String[] parts = node.value.split("/");
        String blue = parts[0];
        String green = parts[1];
        System.out.println("Do you prefer " + blue + " or " + green + "? ");
        String input = console.nextLine();
        if (input.equalsIgnoreCase(blue)) {
            takeSurvey(console, node.left);
        }
        else if (input.equalsIgnoreCase(green)) {
            takeSurvey(console, node.right);
        }
        else {
            System.out.println("Invalid response; try again.");
        }
    }
    /**
     * Export helper method that exports the instance variable
     */
    public void export() {
        System.out.println(getTitle());
        export(root);
    }
    /**
     * Export method to display the survey in the console
     * @param node SurveyTreeNode survey to export
     */
    private void export(SurveyTreeNode node) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            System.out.println("END: " + node.value);
        }
        else {
            System.out.println(node.value);
            export(node.left);
            export(node.right);
        }
    }
    /**
     * Method to replace an answer in the survey with two other answers and choices
     * @param toReplace String value to search for in tree
     * @param leftChoice String choice to point to left answer
     * @param rightChoice String choice to point to right answer
     * @param leftResult String answer that points from left choice
     * @param rightResult String answer that points from right choice
     */
    public void replaceResult(String toReplace, String leftChoice, String
            rightChoice, String leftResult, String rightResult) {
        SurveyTreeNode temp = search(toReplace);
        String choice = leftChoice + "/" + rightChoice;
        temp.value = choice;
        temp.left = new SurveyTreeNode(leftResult);
        temp.right = new SurveyTreeNode(rightResult);
    }
    /**
     * Helper search method
     * @param value String to search for in tree
     * @return SurveyTreeNode in tree that matches value
     */
    public SurveyTreeNode search(String value) {
        return search(value, root);
    }
    /**
     * Search method with binary tree to return SurveyTreeNode that matches the value
     * @param value String to search for
     * @param node SurveyTreeNode to search through
     * @return SurveyTreeNode that matches value
     */
    private SurveyTreeNode search(String value, SurveyTreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.value.equals(value)) {
            return node;
        }
        SurveyTreeNode temp = search(value, node.left);
        if (temp == null) {
            return null;
        }
        if (temp.value.equals(value)) {
            return temp;
        }
        return search(value, node.right);
    }
    /**
     * Internal binary tree node
     */
    static class SurveyTreeNode {
        public String value;
        SurveyTreeNode left;
        SurveyTreeNode right;
        public SurveyTreeNode(String value) {
            if (value.startsWith("END:")) {
                value = value.substring("END:".length());
            }
            this.value = value;
        }
        public boolean isLeaf() {
            return left == null && right == null;
        }
        @Override
        public String toString() {
            return "SurveyTreeNode{" +
                    "value='" + value + '\'' +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}