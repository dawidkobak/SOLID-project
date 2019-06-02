package main;

public class TimeAndZoneConverter {
    TimeAndZoneKeeper timeAndZoneKeeper;
    String zoneAfterConversion;

    public TimeAndZoneConverter(TimeAndZoneKeeper timeAndZoneKeeper, String zoneAfterConversion) {
        validateTimeZone(zoneAfterConversion);
        this.timeAndZoneKeeper = timeAndZoneKeeper;
        this.zoneAfterConversion = zoneAfterConversion;
    }

    private void validateTimeZone(String timeZone) {
        TimeZoneChooser.chceckIfSupported(timeZone);
    }

    public TimeAndZoneKeeper convert() {
        Integer offset = calculateOffset();
        String newTime = createNewTimeWithAddedOffset(offset);
        return new TimeAndZoneKeeper(newTime, zoneAfterConversion);
    }

    private Integer calculateOffset() {
        String zoneToBeConverted = timeAndZoneKeeper.getTimeZone();
        Integer UtcDifferenceFirst = TimeZoneChooser.getUTCDifference(zoneToBeConverted);
        Integer UtcDifferenceSecond = TimeZoneChooser.getUTCDifference(zoneAfterConversion);

        return UtcDifferenceSecond - UtcDifferenceFirst;
    }

    private String createNewTimeWithAddedOffset(Integer offset) {
        String time = timeAndZoneKeeper.getTime();

        String[] timeDevidedOnHoursMinutesAndSecondes = time.split(":");
        String hours = timeDevidedOnHoursMinutesAndSecondes[0];
        String minutes = timeDevidedOnHoursMinutesAndSecondes[1];
        String secondes = timeDevidedOnHoursMinutesAndSecondes[2];

        String newHours = calculateNewHour(hours, offset);
        return createTimeInAppropriateFormat(newHours, minutes, secondes);
    }

    private String calculateNewHour(String oldHour, Integer offset) {
        Integer hours = Integer.valueOf(oldHour);
        hours += offset;
        if (hours < 0) {
            hours += 24;
        }
        hours %= 24;
        String hoursAsString = new String();
        if (hours < 10) {
            hoursAsString += "0";
        }
        hoursAsString += hours;
        return hoursAsString;
    }

    private String createTimeInAppropriateFormat(String hours, String minutes, String secondes) {
        return String.format("%s:%s:%s", hours, minutes, secondes);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException(String.format("Number of input values: %d, should be 3", args.length));
        }
        TimeAndZoneKeeper zoneToBeConverted = new TimeAndZoneKeeper(args[0], args[1]);
        TimeAndZoneConverter timeAndZoneConverter = new TimeAndZoneConverter(zoneToBeConverted, args[2]);
        System.out.println(timeAndZoneConverter.convert());
    }
}
