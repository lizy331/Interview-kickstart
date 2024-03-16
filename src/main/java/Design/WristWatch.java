package Design;

import java.util.Date;

public class WristWatch {
    private Integer alarmHour;
    private Integer alarmMinute;

    public String convertToRomans(int num) {
        // Logic to convert the number to corresponding Roman numeral
        // Implement TDD here
        return "";
    }

    // 如何得到现在的秒钟
    public String currentSeconds() {
        Date now = new Date();
        int seconds = now.getSeconds();
        String formattedSeconds = seconds <= 9 ? "0" + seconds : String.valueOf(seconds);
        return formattedSeconds;
    }

    public void setAlarm(int hour, int minute) {
        alarmHour = hour;
        alarmMinute = minute;
    }

    public String readAlarm() {
        if (alarmHour == null || alarmMinute == null) {
            return "";
        }

        // To represent the hour in a 2-digit format (e.g., 01, 02, 03, ..., 12), %02d is used in the String.format method
        String formattedHour = String.format("%02d", alarmHour);
        String formattedMinute = String.format("%02d", alarmMinute);
        return formattedHour + formattedMinute;
    }

    public void resetAlarm() {
        alarmHour = null;
        alarmMinute = null;
    }


    public static void main(String[] args) {
        WristWatch wristWatch = new WristWatch();

        System.out.println(wristWatch.currentSeconds());
    }
}
