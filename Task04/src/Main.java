import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    private static final Calendar calendar = new Calendar();

    public static void main(String[] args) {
        System.out.println("Calendar console app");
        Scanner scanner = new Scanner(System.in);

        boolean repeat = true;
        while (repeat) {
            printOptions();

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addMeeting();
                case 2 -> removeMeeting();
                case 3 -> displayMeetingsInADay();
                case 4 -> displayMeetingsInADayAndPriority();
                case 5 -> displayMeetingsInADayAndStartTime();
                case 6 -> displayMeetingsInADayAndTimeRange();
                case 7 -> displayMeetingsInADayAndPriorityAndStartTime();
                case 8 -> seedMeetings();
                case 9 -> repeat = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    //region Main Methods

    private static void addMeeting() {
        Day day = inputDay();
        System.out.println("Enter meeting description:");
        String description = getScanner().next();
        LocalTime startTime = inputTime();
        LocalTime endTime = inputTime();
        Priority priority = inputPriority();

        try {
            Meeting meeting = new Meeting(description, startTime, endTime, priority);

            calendar.addMeeting(day, meeting);
        } catch (IllegalArgumentException ex) {
            System.out.println("Unable to add meeting. Ensure that start time is not before " + Meeting.EARLIEST_TIME +
                    ", and start time is not after end time");
        }
    }

    private static void removeMeeting() {
        Day day = inputDay();
        List<Meeting> meetings = calendar.getMeetings(day);
        System.out.println("Which meeting do you want to remove?");
        for (int i = 0; i < meetings.size(); i++) {
            System.out.println(i);
            System.out.println(meetings.get(i));
        }
        int userInput = getScanner().nextInt();
        calendar.removeMeeting(day, meetings.get(userInput));
    }

    private static void displayMeetingsInADay() {
        Day day = inputDay();
        List<Meeting> meetings = calendar.getMeetings(day);
        printMeetings(meetings);
    }

    private static void displayMeetingsInADayAndPriority() {
        Day day = inputDay();
        Priority priority = inputPriority();
        List<Meeting> meetings = calendar.getFilteredMeetings(day, (m) -> m.getPriority() == priority);
        printMeetings(meetings);
    }

    private static void displayMeetingsInADayAndStartTime() {
        Day day = inputDay();
        LocalTime startTime = inputTime();
        Predicate<Meeting> predicate = (m) -> m.getStartTime().isAfter(startTime) || m.getStartTime().equals(startTime);
        List<Meeting> meetings = calendar.getFilteredMeetings(day, predicate);
        printMeetings(meetings);
    }

    private static void displayMeetingsInADayAndTimeRange() {
        Day day = inputDay();
        LocalTime startTime = inputTime();
        LocalTime endTime = inputTime();
        Predicate<Meeting> predicate = (m) ->
                (m.getStartTime().isAfter(startTime) || m.getStartTime().equals(startTime)) &&
                        (m.getEndTime().isBefore(endTime) || m.getEndTime().equals(endTime));
        List<Meeting> meetings = calendar.getFilteredMeetings(day, predicate);
        printMeetings(meetings);
    }

    private static void displayMeetingsInADayAndPriorityAndStartTime() {
        Day day = inputDay();
        Priority priority = inputPriority();
        LocalTime startTime = inputTime();
        Predicate<Meeting> predicate = (m) ->
                m.getPriority() == priority &&
                        (m.getStartTime().isAfter(startTime) || m.getStartTime().equals(startTime));
        List<Meeting> meetings = calendar.getFilteredMeetings(day, predicate);
        printMeetings(meetings);
    }

    private static void seedMeetings() {
        Day day = inputDay();

        Meeting meeting1 = new Meeting("Meeting 1", LocalTime.of(7, 0), LocalTime.of(20, 30), Priority.HIGH);
        Meeting meeting2 = new Meeting("Meeting 2", LocalTime.of(17, 0), LocalTime.of(22, 30), Priority.NORMAL);
        Meeting meeting3 = new Meeting("Meeting 3", LocalTime.of(14, 0), LocalTime.of(22, 30), Priority.HIGH);
        Meeting meeting4 = new Meeting("Meeting 4", LocalTime.of(9, 0), LocalTime.of(14, 20), Priority.HIGH);
        Meeting meeting5 = new Meeting("Meeting 5", LocalTime.of(10, 0), LocalTime.of(22, 30), Priority.NORMAL);
        Meeting meeting6 = new Meeting("Meeting 6", LocalTime.of(20, 0), LocalTime.of(21, 45), Priority.HIGH);
        Meeting meeting7 = new Meeting("Meeting 7", LocalTime.of(7, 0), LocalTime.of(10, 10), Priority.URGENT);

        calendar.addMeeting(day, meeting1);
        calendar.addMeeting(day, meeting2);
        calendar.addMeeting(day, meeting3);
        calendar.addMeeting(day, meeting4);
        calendar.addMeeting(day, meeting5);
        calendar.addMeeting(day, meeting6);
        calendar.addMeeting(day, meeting7);
    }

    //endregion

    //region Input methods

    private static Day inputDay() {
        while (true) {
            try {
                System.out.println("Enter day (MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY):");
                return Day.valueOf(getScanner().next());
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid day. Please enter a valid day");
            }
        }
    }

    private static Priority inputPriority() {
        while (true) {
            try {
                System.out.println("Enter meeting priority (NORMAL, HIGH, URGENT):");
                return Priority.valueOf(getScanner().next());
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid priority. Please enter a valid priority");
            }
        }
    }

    private static LocalTime inputTime() {
        while (true) {
            try {
                System.out.println("Enter time (HH:MM):");
                return getTime(getScanner().next());
            } catch (Exception ex) {
                System.out.println("Invalid time. Please enter a valid time");
            }
        }
    }

    //endregion

    //region Print methods

    private static void printOptions() {
        System.out.println("Add meeting [1]");
        System.out.println("Remove meeting [2]");
        System.out.println("Show meetings in a day [3]");
        System.out.println("Show meetings in a day and priority [4]");
        System.out.println("Show meetings in a day within start time [5]");
        System.out.println("Show meetings in a day within time range [6]");
        System.out.println("Show meetings in a day with priority and within start time [7]");
        System.out.println("Seed meetings [8]");
        System.out.println("Exit [9]");
    }

    private static void printMeetings(List<Meeting> meetings) {
        for (Meeting meeting : meetings.stream().sorted().toList()) {
            System.out.println(meeting);
        }
    }

    //endregion

    //region Utils

    private static Scanner getScanner() {
        return new Scanner(System.in);
    }

    private static LocalTime getTime(String time) {
        String[] parts = time.split(":");
        return LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    //endregion
}