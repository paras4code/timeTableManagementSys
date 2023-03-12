import java.util.*;

class TimeTable {
    private String[][] schedule;

    public TimeTable() {
        schedule = new String[5][8];
    }

    public void addClass(String className, String day, int hour) {
        int dayIndex = getDayIndex(day);
        schedule[dayIndex][hour - 1] = className;
    }

    public void removeClass(String day, int hour) {
        int dayIndex = getDayIndex(day);
        schedule[dayIndex][hour - 1] = null;
    }