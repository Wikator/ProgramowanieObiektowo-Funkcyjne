import java.time.LocalTime;

public class Meeting implements Comparable<Meeting> {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;

    public Meeting(String description, LocalTime startTime, LocalTime endTime, Priority priority) {

        if (startTime.isBefore(EARLIEST_TIME)) {
            throw new IllegalArgumentException("Start time must be after " + EARLIEST_TIME);
        }

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public static final LocalTime EARLIEST_TIME = LocalTime.of(6, 0);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        if (startTime.isBefore(EARLIEST_TIME)) {
            throw new IllegalArgumentException("Start time must be after " + EARLIEST_TIME);
        }

        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        this.endTime = endTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
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
