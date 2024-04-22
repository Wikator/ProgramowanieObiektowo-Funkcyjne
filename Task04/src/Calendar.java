import java.util.*;

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

    public List<Meeting> getMeetings() {
        List<Meeting> allMeetings = new ArrayList<>();
        for (List<Meeting> dayMeetings : meetings.values()) {
            allMeetings.addAll(dayMeetings);
        }
        return allMeetings;
    }

    public List<Meeting> getMeetings(Priority priority) {
        List<Meeting> allMeetings = new ArrayList<>();
        for (List<Meeting> dayMeetings : meetings.values()) {
            allMeetings.addAll(dayMeetings.stream().filter((m) -> m.getPriority() == priority).toList());
        }
        return allMeetings;
    }

    public void removeMeeting(Day day, Meeting meeting) {
        meetings.get(day).remove(meeting);
    }
}
