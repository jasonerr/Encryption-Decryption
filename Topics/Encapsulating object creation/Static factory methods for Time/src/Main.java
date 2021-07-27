import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    public static Time noon() {
        return of(12, 0, 0);
    }

    public static Time midnight() {
        return of(0, 0, 0);
    }

    public static Time ofSeconds(long seconds) {
        int s = (int) seconds % 60;
        int m = (int) seconds / 60 % 60;
        int h = (int) seconds / 3600 > 23 ? (int) seconds / 3600 % 24 : (int) seconds / 3600;
        return of(h, m, s);
    }

    public static Time of(int hour, int minute, int second) {
        // write your code here
        Time time = new Time();
        time.hour = hour;
        time.minute = minute;
        time.second = second;
        if (isValidHour(hour) && isValidMinute(minute) && isValidSecond(second)) {
            return time;
        } else {
            return null;
        }
    }

    private static boolean isValidHour(int hour) {
        return hour < 24 && hour >= 0;
    }

    private static boolean isValidMinute(int minute) {
        return minute < 60 && minute >= 0;
    }

    private static boolean isValidSecond(int second) {
        return second < 60 && second >= 0;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }
}