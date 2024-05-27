import java.time.LocalTime;

public abstract sealed class CalendarEntry implements Comparable<CalendarEntry> permits Meeting, Task  {
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected String description;

    public CalendarEntry(LocalTime startTime, LocalTime endTime, String description) {
        if (startTime.isBefore(EARLIEST_TIME)) {
            throw new IllegalArgumentException("Start time must be after " + EARLIEST_TIME);
        }

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public static final LocalTime EARLIEST_TIME = LocalTime.of(6, 0);

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public abstract String toString();
}
