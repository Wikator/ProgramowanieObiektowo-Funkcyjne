import java.time.LocalTime;

public record Meeting(String description, LocalTime startTime, LocalTime endTime,
                      Priority priority) implements Comparable<Meeting> {
    public Meeting {

        if (startTime.isBefore(EARLIEST_TIME)) {
            throw new IllegalArgumentException("Start time must be after " + EARLIEST_TIME);
        }

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }

    }

    public static final LocalTime EARLIEST_TIME = LocalTime.of(6, 0);

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
