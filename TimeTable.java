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
    
    public void display() {
        System.out.println("Time Table");
        System.out.println("-----------");
        System.out.println("Day\t\t1\t2\t3\t4\t5\t6\t7\t8");
        System.out.println("-----------");
        String[] days = { "Monday\t", "Tuesday\t", "Wednesday", "Thursday", "Friday\t" };
        for (int i = 0; i < days.length; i++) {
            System.out.print(days[i] + "\t");
            for (int j = 0; j < 8; j++) {
                if (schedule[i][j] != null) {
                    System.out.print(schedule[i][j]);
                } else {
                    System.out.print("-");
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    
        private int getDayIndex(String day) {
        int index = -1;
        switch (day) {
            case "Monday":
                index = 0;
                break;
            case "Tuesday":
                index = 1;
                break;
            case "Wednesday":
                index = 2;
                break;
            case "Thursday":
                index = 3;
                break;
            case "Friday":
                index = 4;
                break;
        }
        return index;
    }
}

