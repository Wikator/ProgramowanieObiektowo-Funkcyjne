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
                case 2 -> addTask();
                case 3 -> removeMeeting();
                case 4 -> removeTask();
                case 5 -> displayMeetingsInADay();
                case 6 -> displayTasksInADay();
                case 7 -> displayMeetingsInADayAndPriority();
                case 8 -> displayTasksInADayAndStatus();
                case 9 -> displayMeetingsInADayAndPriorityAndStartTime();
                case 10 -> displayTasksInADayAndStatusAndEndTime();
                case 11 -> seedMeetingsAndTasks();
                case 12 -> repeat = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    //region Main Methods

    private static void addMeeting() {
        Day day = inputDay();
        String description = inputDescription();
        LocalTime startTime = inputTime();
        LocalTime endTime = inputTime();
        Priority priority = inputPriority();

        try {
            Meeting meeting = new Meeting(description, startTime, endTime, priority);

            calendar.addEntry(day, meeting);
        } catch (IllegalArgumentException ex) {
            System.out.println("Unable to add meeting. Ensure that start time is not before " + Meeting.EARLIEST_TIME +
                    ", and start time is not after end time");
        }
    }

    private static void addTask() {
        Day day = inputDay();
        String description = inputDescription();
        LocalTime startTime = inputTime();
        LocalTime endTime = inputTime();
        Status status = inputStatus();

        try {
            Task task = new Task(description, startTime, endTime, status);

            calendar.addEntry(day, task);
        } catch (IllegalArgumentException ex) {
            System.out.println("Unable to add task. Ensure that start time is not before " + Task.EARLIEST_TIME +
                    ", and start time is not after end time");
        }
    }

    private static void removeMeeting() {
        Day day = inputDay();
        List<Meeting> meetings = calendar.getMeetings(day);
        System.out.println("Which meeting do you want to remove?");
        printEntriesWithIndexes(meetings);
        int userInput = getScanner().nextInt();
        calendar.removeEntry(day, meetings.get(userInput));
    }

    private static void removeTask() {
        Day day = inputDay();
        List<Task> tasks = calendar.getTasks(day);
        System.out.println("Which task do you want to remove?");
        printEntriesWithIndexes(tasks);
        int userInput = getScanner().nextInt();
        calendar.removeEntry(day, tasks.get(userInput));
    }

    private static void displayMeetingsInADay() {
        Day day = inputDay();
        List<Meeting> meetings = calendar.getMeetings(day);
        printEntries(meetings);
    }

    private static void displayTasksInADay() {
        Day day = inputDay();
        List<Task> tasks = calendar.getTasks(day);
        printEntries(tasks);
    }

    private static void displayMeetingsInADayAndPriority() {
        Day day = inputDay();
        Priority priority = inputPriority();
        List<Meeting> meetings = calendar.getFilteredMeetings(day, (m) -> m.getPriority() == priority);

        printEntries(meetings);
    }

    private static void displayTasksInADayAndStatus() {
        Day day = inputDay();
        Status status = inputStatus();
        List<Task> tasks = calendar.getFilteredTasks(day, (t) -> t.getStatus() == status);

        printEntries(tasks);
    }

    private static void displayMeetingsInADayAndPriorityAndStartTime() {
        Day day = inputDay();
        LocalTime startTime = inputTime();
        Priority priority = inputPriority();
        Predicate<Meeting> predicate = (m) -> (m.getStartTime().isAfter(startTime) || m.getStartTime().equals(startTime))
                && m.getPriority() == priority;
        List<Meeting> meetings = calendar.getFilteredMeetings(day, predicate);
        printEntries(meetings);
    }

    private static void displayTasksInADayAndStatusAndEndTime() {
        Day day = inputDay();
        LocalTime endTime = inputTime();
        Status status = inputStatus();
        Predicate<Task> predicate = (t) -> (t.getEndTime().isBefore(endTime) || t.getEndTime().equals(endTime))
                && t.getStatus() == status;
        List<Task> tasks = calendar.getFilteredTasks(day, predicate);
        printEntries(tasks);
    }

    private static void seedMeetingsAndTasks() {
        Day day = inputDay();

        Meeting meeting1 = new Meeting("Meeting 1", LocalTime.of(7, 0), LocalTime.of(20, 30), Priority.HIGH);
        Meeting meeting2 = new Meeting("Meeting 2", LocalTime.of(17, 0), LocalTime.of(22, 30), Priority.NORMAL);
        Meeting meeting3 = new Meeting("Meeting 3", LocalTime.of(14, 0), LocalTime.of(22, 30), Priority.HIGH);
        Meeting meeting4 = new Meeting("Meeting 4", LocalTime.of(9, 0), LocalTime.of(14, 20), Priority.HIGH);
        Meeting meeting5 = new Meeting("Meeting 5", LocalTime.of(10, 0), LocalTime.of(22, 30), Priority.NORMAL);
        Meeting meeting6 = new Meeting("Meeting 6", LocalTime.of(20, 0), LocalTime.of(21, 45), Priority.HIGH);
        Meeting meeting7 = new Meeting("Meeting 7", LocalTime.of(7, 0), LocalTime.of(10, 10), Priority.URGENT);

        Task task1 = new Task("Task 1", LocalTime.of(7, 0), LocalTime.of(20, 30), Status.PLANNED);
        Task task2 = new Task("Task 2", LocalTime.of(17, 0), LocalTime.of(22, 30), Status.IN_PROGRESS);
        Task task3 = new Task("Task 3", LocalTime.of(14, 0), LocalTime.of(22, 30), Status.COMPLETED);
        Task task4 = new Task("Task 4", LocalTime.of(9, 0), LocalTime.of(14, 20), Status.PLANNED);
        Task task5 = new Task("Task 5", LocalTime.of(10, 0), LocalTime.of(22, 30), Status.IN_PROGRESS);
        Task task6 = new Task("Task 6", LocalTime.of(20, 0), LocalTime.of(21, 45), Status.COMPLETED);
        Task task7 = new Task("Task 7", LocalTime.of(7, 0), LocalTime.of(10, 10), Status.COMPLETED);

        calendar.addEntry(day, meeting1);
        calendar.addEntry(day, meeting2);
        calendar.addEntry(day, meeting3);
        calendar.addEntry(day, meeting4);
        calendar.addEntry(day, meeting5);
        calendar.addEntry(day, meeting6);
        calendar.addEntry(day, meeting7);

        calendar.addEntry(day, task1);
        calendar.addEntry(day, task2);
        calendar.addEntry(day, task3);
        calendar.addEntry(day, task4);
        calendar.addEntry(day, task5);
        calendar.addEntry(day, task6);
        calendar.addEntry(day, task7);
    }

    //endregion

    //region Input methods

    private static String inputDescription() {
        System.out.println("Enter description:");
        return getScanner().next();
    }

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

    private static Status inputStatus() {
        while (true) {
            try {
                System.out.println("Enter task status (PLANNED, CONFIRMED, IN_PROGRESS, COMPLETED):");
                return Status.valueOf(getScanner().next());
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid status. Please enter a valid status");
            }
        }
    }

    //endregion

    //region Print methods

    private static void printOptions() {
        System.out.println("Add meeting [1]");
        System.out.println("Add task [2]");
        System.out.println("Remove meeting [3]");
        System.out.println("Remove task [4]");
        System.out.println("Show meetings in a day [5]");
        System.out.println("Show tasks in a day [6]");
        System.out.println("Show meetings in a day and priority [7]");
        System.out.println("Show tasks in a day and status [8]");
        System.out.println("Show meetings in a day, priority, and within start time [9]");
        System.out.println("Show tasks in a day, status, and within end time [10]");
        System.out.println("Seed meetings and tasks [11]");
        System.out.println("Exit [12]");
    }

    private static void printEntries(List<? extends CalendarEntry> entries) {
        entries.stream().sorted().forEach(System.out::println);
    }

    private static void printEntriesWithIndexes(List<? extends CalendarEntry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(i);
            System.out.println(entries.get(i));
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