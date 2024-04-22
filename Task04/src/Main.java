import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Calendar calendar = new Calendar();

    public static void main(String[] args) {
        System.out.println("Calendar console app");

        boolean repeat = true;
        while (repeat) {
            System.out.println("Add meeting [1]");
            System.out.println("Remove meeting [2]");
            System.out.println("Show meetings in a day [3]");
            System.out.println("Show meetings in a day and priority [4]");
            System.out.println("Show meetings in a day within start time [5]");
            System.out.println("Seed meetings [6]");
            System.out.println("Exit [7]");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    enterDay();
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
                    enterDay();
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
                    enterDay();
                    String day = scanner.next();
                    List<Meeting> meetings = calendar.getMeetings(Day.valueOf(day));
                    for (Meeting meeting : meetings.stream().sorted().toList()) {
                        System.out.println(meeting);
                    }
                }
                case 6 -> {
                    enterDay();
                    Day day = inputDay();
                    Meeting meeting1 = new Meeting("Meeting 1", 7 * 60, 22 * 60, Priority.HIGH);
                    Meeting meeting2 = new Meeting("Meeting 2", 17 * 60, 22 * 60, Priority.NORMAL);
                    Meeting meeting3 = new Meeting("Meeting 3", 14 * 60, 22 * 60, Priority.HIGH);
                    Meeting meeting4 = new Meeting("Meeting 4", 9 * 60, 22 * 60, Priority.HIGH);
                    Meeting meeting5 = new Meeting("Meeting 5", 10 * 60, 22 * 60, Priority.NORMAL);
                    Meeting meeting6 = new Meeting("Meeting 6", 20 * 60, 22 * 60, Priority.HIGH);
                    Meeting meeting7 = new Meeting("Meeting 7", 7 * 60, 22 * 60, Priority.URGENT);

                    calendar.addMeeting(day, meeting1);
                    calendar.addMeeting(day, meeting2);
                    calendar.addMeeting(day, meeting3);
                    calendar.addMeeting(day, meeting4);
                    calendar.addMeeting(day, meeting5);
                    calendar.addMeeting(day, meeting6);
                    calendar.addMeeting(day, meeting7);
                }
                case 7 -> repeat = false;
                default ->
                    System.out.println("Invalid choice");
            }
        }
    }

    private static Integer getTime(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private static void enterDay() {
        System.out.println("Enter day (MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY):");
    }

    private static Day inputDay() {
        return Day.valueOf(scanner.next());
    }
}