import java.time.LocalTime;

public final class Task extends CalendarEntry {

    private final Status status;

    public Task(String description, LocalTime startTime, LocalTime endTime, Status status) {
        super(startTime, endTime, description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Description: " + this.description +
                "\nStart time: " + startTime +
                "\nEnd time: " + endTime +
                "\nStatus: " + status +
                "\n";
    }

    @Override
    public int compareTo(CalendarEntry o) {
        return this.startTime.compareTo(o.startTime);
    }
}
