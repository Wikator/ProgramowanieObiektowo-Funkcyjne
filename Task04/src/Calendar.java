import java.util.*;
import java.util.function.Predicate;

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

    public void removeMeeting(Day day, Meeting meeting) {
        meetings.get(day).remove(meeting);
    }

    public List<Meeting> getMeetings(Day day) {
        return meetings.get(day);
    }

    public List<Meeting> getFilteredMeetings(Day day, Predicate<Meeting> predicate) {
        // return meetings.get(day).stream().filter(predicate).toList();

        List<Meeting> filteredMeetings = new ArrayList<>();
        for (Meeting meeting : getMeetings(day)) {
            if (predicate.test(meeting)) {
                filteredMeetings.add(meeting);
            }
        }
        return filteredMeetings;
    }
}
