public class Meeting implements Comparable<Meeting> {
    private String description;
    private int startTime;
    private int endTime;
    private Priority priority;

    public Meeting(String description, int startTime, int endTime, Priority priority) {

        if (startTime < EARLIEST_TIME) {
            throw new IllegalArgumentException("Start time must be after " + getTime(EARLIEST_TIME));
        }

        if (endTime < startTime) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public static final int EARLIEST_TIME = 60 * 6;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        if (startTime < EARLIEST_TIME) {
            throw new IllegalArgumentException("Start time must be after " + getTime(EARLIEST_TIME));
        }

        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        if (endTime < startTime) {
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
                "\nStart time: " + getTime(startTime) +
                "\nEnd time: " + getTime(endTime) +
                "\nPriority: " + priority +
                "\n";
    }

    private String getTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;

        String minutesString;
        if (minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = Integer.toString(minutes);
        }

        String secondsString;
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = Integer.toString(seconds);
        }

        return minutesString + ":" + secondsString;
    }

    @Override
    public int compareTo(Meeting meeting) {
        return Integer.compare(startTime, meeting.getStartTime());
    }
}
