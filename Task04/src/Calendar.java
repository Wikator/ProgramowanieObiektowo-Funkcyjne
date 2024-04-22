import java.util.*;
import java.time.LocalTime;

public class Calendar {
    private final Map<Day, List<Meeting>> meetings;

    public Calendar() {
        meetings = new HashMap<>();
        meetings.put(Day.MONDAY, new ArrayList<>());
        meetings.put(Day.TUESDAY, new ArrayList<>());
        meetings.put(Day.WEDNESDAY, new ArrayList<>());
        meetings.put(Day.THURSDAY, new ArrayList<>());
        meetings.put(Day.FRIDAY, new ArrayList<>());
        meetings.put(Day.SATURDAY, new ArrayList<>());
        meetings.put(Day.SUNDAY, new ArrayList<>());
    }

    public void addMeeting(Day day, Meeting meeting) {
        meetings.get(day).add(meeting);
    }

    public List<Meeting> getMeetings(Day day) {
        return meetings.get(day);
    }

    public List<Meeting> getMeetings(Day day, Priority priority) {
        return meetings.get(day).stream().filter((m) -> m.priority() == priority).toList();
    }

    public List<Meeting> getMeetings(Day day, LocalTime time) {
        return meetings.get(day).stream().filter((m) -> time.isBefore(m.startTime())).toList();
    }

    public void removeMeeting(Day day, Meeting meeting) {
        meetings.get(day).remove(meeting);
    }
}
