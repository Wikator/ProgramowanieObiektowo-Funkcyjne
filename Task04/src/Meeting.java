import java.time.LocalTime;

public final class Meeting extends CalendarEntry {
    private final Priority priority;

    public Meeting(String description, LocalTime startTime, LocalTime endTime,
                   Priority priority) {

        super(startTime, endTime, description);
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }


    @Override
    public String toString() {
        return "Description: " + this.description +
                "\nStart time: " + startTime +
                "\nEnd time: " + endTime +
                "\nPriority: " + priority +
                "\n";
    }

    @Override
    public int compareTo(CalendarEntry o) {
        return this.startTime.compareTo(o.startTime);
    }
}
