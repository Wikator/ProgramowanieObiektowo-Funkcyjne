import java.util.*;
import java.util.function.Predicate;

public class Calendar {
    private final Map<Day, List<CalendarEntry>> meetings;

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

    public void addEntry(Day day, CalendarEntry entry) {
        meetings.get(day).add(entry);
    }

    public void removeEntry(Day day, CalendarEntry entry) {
        meetings.get(day).remove(entry);
    }

    public List<Meeting> getMeetings(Day day) {
        return getFilteredEntries(day, m -> m instanceof Meeting).stream()
                .map(m -> (Meeting) m)
                .toList();
    }

    public List<Task> getTasks(Day day) {
        return getFilteredEntries(day, m -> m instanceof Task).stream()
                .map(m -> (Task) m)
                .toList();
    }

    public List<Meeting> getFilteredMeetings(Day day, Predicate<Meeting> predicate) {
        return getFilteredEntries(day, m -> m instanceof Meeting).stream()
                .map(m -> (Meeting) m)
                .filter(predicate)
                .toList();
    }

    public List<Task> getFilteredTasks(Day day, Predicate<Task> predicate) {
        return getFilteredEntries(day, m -> m instanceof Task).stream()
                .map(m -> (Task) m)
                .filter(predicate)
                .toList();
    }


    private List<CalendarEntry> getFilteredEntries(Day day, Predicate<CalendarEntry> predicate) {
        return meetings.get(day).stream().filter(predicate).toList();
    }
}
