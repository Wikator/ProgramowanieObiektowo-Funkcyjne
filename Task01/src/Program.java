import java.util.Scanner;

public class Program {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Program obliczający 2 do potęgi n, oraz sumy liczb od a do b.");
        boolean repeat = true;

        while (repeat) {
            String userOperationChoice =
                    readStringFromUser("Chcesz obliczyć 2 do potęgi n [1], czy sumę liczb od a do b [2]?: ");

            switch (userOperationChoice) {
                case "1" -> {
                    int number = readNumberFromUser("Podaj liczbę naturalną: ");

                    System.out.print("Twoim wynikiem jest: ");
                    System.out.println(Calculations.power(number));
                }
                case "2" -> {
                    int startingNumber = readNumberFromUser("Podaj liczbę początkową: ");
                    int endingNumber = readNumberFromUser("Podaj liczbe końcową: ");

                    System.out.print("Twoim wynikiem jest: ");
                    System.out.println(Calculations.sum(startingNumber, endingNumber));
                }
                default -> System.out.println("Niepoprawna operacja");
            }

            String userRepeatChoice = readStringFromUser("Czy chcesz wykonać kolejną operację? [y, N]: ");
            repeat = userRepeatChoice.equalsIgnoreCase("y");
        }

        System.out.println("Koniec programu.");
    }

    private static int readNumberFromUser(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    private static String readStringFromUser(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
