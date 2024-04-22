import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Calendar calendar = new Calendar();

    public static void main(String[] args) {
        System.out.println("Calendar console app");

        boolean repeat = true;
        while (repeat) {
            System.out.println("Add meeting [1]");
            System.out.println("Remove meeting [2]");
            System.out.println("Show meetings [3]");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter day (MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY):");
                    String day = scanner.next();
                    System.out.println("Enter meeting description:");
                    String description = scanner.next();
                    System.out.println("Enter meeting start time (example: 08:20):");
                    String startTime = scanner.next();
                    System.out.println("Enter meeting end time (example: 08:20):");
                    String endTime = scanner.next();
                    System.out.println("Enter meeting priority (NORMAL, HIGH, URGENT):");
                    String priority = scanner.next();
                    Meeting meeting = new Meeting(description, getTime(startTime), getTime(endTime),
                            Priority.valueOf(priority));

                    calendar.addMeeting(Day.valueOf(day), meeting);
                }

                case 2 -> {
                    System.out.println("From which day do you want to remove meeting? ");
                    Day day = Day.valueOf(scanner.next());
                    List<Meeting> meetings = calendar.getMeetings(day);
                    System.out.println("Which meeting do you want to remove?");
                    for (int i = 0; i < meetings.size(); i++) {
                        System.out.println(i);
                        System.out.println(meetings.get(i));
                    }
                    int userInput = scanner.nextInt();
                    calendar.removeMeeting(day, meetings.get(userInput));
                }
                case 3 -> {
                    System.out.println("Enter day (MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY):");
                    String day = scanner.next();
                    List<Meeting> meetings = calendar.getMeetings(Day.valueOf(day));
                    for (Meeting meeting : meetings.stream().sorted().toList()) {
                        System.out.println(meeting);
                    }
                }
                default ->
                    System.out.println("Invalid choice");
            }
        }
    }

    private static Integer getTime(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}