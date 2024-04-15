import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final GradeList gradeList = new GradeList();

    public static void main(String[] args) {
        System.out.println("Program to save student grades\n");

        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;

        while (repeat) {
            printOptions();

            switch (scanner.next()) {
                case "1" -> tryAddGrade(scanner);
                case "2" -> printAverage();
                case "3" -> printMaximumGrade();
                case "4" -> printMinimumGrade();
                case "5" -> {
                    System.out.println("Exiting");
                    repeat = false;
                }
                default -> System.out.println("Invalid command");
            }
        }
    }

    private static void printOptions() {
        System.out.println("What do you want to do?");
        System.out.println("Add grade [1]");
        System.out.println("Get average [2]");
        System.out.println("Get highest grade [3]");
        System.out.println("Get lowest grade [4]");
        System.out.println("Exit [5]");
    }

    private static void tryAddGrade(Scanner scanner) {
        System.out.print("Give a grade: ");
        Double grade = Double.parseDouble(scanner.next());

        if (gradeList.add(grade))
            System.out.println("Grade added successfully");
        else
            System.out.println("Invalid grade");
    }

    private static void printAverage() {
        Optional<Double> average = gradeList.average();
        if (average.isPresent())
            System.out.printf("Average grade: %f\n", average.get());
        else
            System.out.println("No grades are currently saved. No average exists");
    }

    private static void printMaximumGrade() {
        Optional<Double> maximum = gradeList.maxValue();
        if (maximum.isPresent())
            System.out.printf("Maximum grade: %f\n", maximum.get());
        else
            System.out.println("No grades are currently saved");
    }

    private static void printMinimumGrade() {
        Optional<Double> minimum = gradeList.minValue();
        if (minimum.isPresent())
            System.out.printf("Minimum grade: %f\n", minimum.get());
        else
            System.out.println("No grades are currently saved");
    }
}