import java.time.LocalTime;

public class Meeting implements Comparable<Meeting> {

    private final String description;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Priority priority;

    public Meeting(String description, LocalTime startTime, LocalTime endTime,
                   Priority priority) {

        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;

        if (startTime.isBefore(EARLIEST_TIME)) {
            throw new IllegalArgumentException("Start time must be after " + EARLIEST_TIME);
        }

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }

    }

    public static final LocalTime EARLIEST_TIME = LocalTime.of(6, 0);

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
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
    public int compareTo(Meeting meeting) {
        return this.startTime.compareTo(meeting.startTime);
    }
}
