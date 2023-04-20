import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This class allows the user to take a survey within the console. It is the main class to use the operations from
 * surveyTree.java.
 */
public class SurveyClient {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter survey file to read: ");
        String inFileName = scan.nextLine();
        File inFile = new File(inFileName);
        while (!inFile.exists()) {
            System.out.println("  File does not exist. Please try again.");
            System.out.print("Enter survey file to read: ");
            inFileName = scan.nextLine();
            inFile = new File(inFileName);
        }
        SurveyTree survey = new SurveyTree(new Scanner(inFile));
        System.out.println("Survey created!");
        System.out.println("What is your favorite " + survey.getTitle() + "?");
        System.out.println();
        String option = "";
        while (!option.equalsIgnoreCase("quit")) {
            option = operation(scan);
            System.out.println();
            switch (option.toLowerCase()) {
                case "take" -> {
                    survey.takeSurvey(scan);
                    System.out.println();
                }
                case "replace" -> {
                    replaceResult(scan, survey);
                }
                case "export" -> {
                    survey.export();
                    System.out.println("Survey exported!");
                    System.out.println();
                }
                case "quit" -> {
                    System.out.println("Thanks for taking the survey!");
                }
                default -> System.out.println("  Invalid choice. Please try again.");
            }
        }
    }
    /**
     * Displays a menu to the user and returns user answer for intended operation with survey.
     * @param scan Scanner to record user input
     * @return String user input
     */
    private static String operation(Scanner scan) {
        System.out.println("What would you like to do? Choose an option in brackets.");
        System.out.println("  [take] survey");
        System.out.println("  [replace] result");
        System.out.println("  [export] survey");
        System.out.println("  [quit] program");
        return scan.nextLine();
    }

    /**
     * Prompts the user to replace result after they choose the replace operation.
     * @param scan Scanner to record user input
     * @param survey current survey they are taking
     */
    private static void replaceResult(Scanner scan, SurveyTree survey) {
        System.out.print("Enter end result to replace: ");
        String toReplace = scan.nextLine();
        System.out.print("Enter left choice: ");
        String leftChoice = scan.nextLine();
        System.out.print("Enter right choice: ");
        String rightChoice = scan.nextLine();
        System.out.print("Enter left result: ");
        String leftResult = scan.nextLine();
        System.out.print("Enter right result: ");
        String rightResult = scan.nextLine();
        survey.replaceResult(toReplace, leftChoice, rightChoice, leftResult,
                rightResult);
    }
}