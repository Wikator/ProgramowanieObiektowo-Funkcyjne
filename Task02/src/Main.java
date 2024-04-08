import java.util.Scanner;

public class Main {
    static Cylinder cylinder = new Cylinder();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Program for working with cylinders");
        boolean repeatLoop = true;

        while (repeatLoop) {
            printOptions();

            switch (scanner.next()) {
                case "1" -> printRadiusAndHeight();
                case "2" -> setRadiusAndHeight();
                case "3" -> printAreas();
                case "4" -> printVolume();
                case "5" -> repeatLoop = false;
                default -> printInvalidCommand();
            }
        }
    }

    private static void printOptions() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("Print values [1]");
        System.out.println("Set values [2]");
        System.out.println("Print total, base, and wall area [3]");
        System.out.println("Print volume [4]");
        System.out.println("Exit [5]");
    }

    private static void printRadiusAndHeight() {
        System.out.printf("Radius: %f\nHeight: %f\n",
                cylinder.getRadius(), cylinder.getHeight());
    }

    private static void setRadiusAndHeight() {
        System.out.print("Radius: ");
        String radius = scanner.next();
        System.out.print("Height: ");
        String height = scanner.next();
        cylinder.setRadius(Double.parseDouble(radius));
        cylinder.setHeight(Double.parseDouble(height));
    }

    private static void printAreas() {
        System.out.printf("Base area: %f\nWall area: %f\nTotalArea: %f\n",
                cylinder.baseArea(), cylinder.wallArea(), cylinder.totalArea());
    }

    private static void printVolume() {
        System.out.printf("Volume: %f\n", cylinder.volume());
    }

    private static void printInvalidCommand() {
        System.out.println("Invalid command");
    }
}