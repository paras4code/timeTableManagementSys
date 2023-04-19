import java.io.*;
import java.util.*;

class MySchedule {
    private String[][] schedule;

    public MySchedule() {
        schedule = new String[5][8];
    }

    public void addClass(String className, String day, int hour) throws IllegalArgumentException {
        int dayIndex = getDayIndex(day);
        if (dayIndex == -1 || hour < 1 || hour > 8) {
            throw new IllegalArgumentException("Invalid day or hour");
        }
        schedule[dayIndex][hour - 1] = className;
    }

    public void removeClass(String day, int hour) throws IllegalArgumentException{
        int dayIndex = getDayIndex(day);
        if (dayIndex == -1 || hour < 1 || hour > 8) {
            throw new IllegalArgumentException("Invalid day or hour");
        }
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

    public void saveToFile(String fileName, String classname, String day, int hour) {
        try {

            FileWriter writer = new FileWriter(fileName);
            writer.write("\n");
            writer.write("Subject Name - " + classname + " | day - " + day + "| hour - " + hour);
            writer.close();
            System.out.println("Time table saved to file " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            int row = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] classes = line.split(",");
                for (int i = 0; i < classes.length; i++) {
                    if (!classes[i].equals("")) {
                        schedule[row][i] = classes[i];
                    }
                }
                row++;
            }
            reader.close();
            System.out.println("Time table loaded from file " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file.");
            e.printStackTrace();
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

public class TimeTableManagementSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MySchedule table = new MySchedule();
        String choice = "";
        String filename = "schedule.txt";
        String className = "";
        String day = "";
        int hour = 0;

        while (!choice.equals("4")) {
            System.out.println("Time Table Management System");
            System.out.println("----------------------------");
            System.out.println("1. Add Class");
            System.out.println("2. Remove Class");
            System.out.println("3. Display Time Table");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextLine();

            switch (choice) {
                case "1":
                try{
                    System.out.print("Enter class name: ");
                    className = input.nextLine();
                    System.out.print("Enter day (Monday-Friday): ");
                    day = input.nextLine();
                    System.out.print("Enter hour (1-8): ");
                    hour = Integer.parseInt(input.nextLine());
                    table.addClass(className, day, hour);
                    System.out.println("Class added successfully!");
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case "2":
                try
                {
                    System.out.print("Enter day (Monday-Friday): ");
                    day = input.nextLine();
                    System.out.print("Enter hour (1-8): ");
                    hour = Integer.parseInt(input.nextLine());
                    table.removeClass(day, hour);
                    System.out.println("Class removed successfully!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid input. Please enter a valid day (Monday-Friday) and hour (1-8).");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid hour (1-8).");
                }
                    break;
                case "3":
                    table.display();
                    break;
                case "4":
                    System.out.println("Thank you for using Time Table Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            // Save changes to file after each operation
            table.saveToFile(filename, className, day, hour);

            System.out.println();
        }

        // Close input scanner
        input.close();
    }
}
