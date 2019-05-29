package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeAndZoneKeeper {
    private String time;
    private String timeZone;

    public TimeAndZoneKeeper(String time, String timeZone) {
        validateTime(time);
        this.time = time;
        validateTimeZone(timeZone);
        this.timeZone = timeZone;
    }

    private void validateTime(String time) {
        Pattern timePattern = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");
        Matcher matcher = timePattern.matcher(time);
        if(!matcher.matches()) {
            throw new IllegalArgumentException("Bad time, format should be HH:mm:ss");
        }
    }

    private void validateTimeZone(String timeZone) {
        TimeZoneChooser.chceckIfSupported(timeZone);
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", time, timeZone);
    }
}

