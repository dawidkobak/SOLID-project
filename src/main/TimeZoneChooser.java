package main;

import java.util.HashMap;
import java.util.Map;

public class TimeZoneChooser {
    private static Map<String, Integer> supportedZonesAndTheirUTC;

    static {
        supportedZonesAndTheirUTC = new HashMap<>();
        supportedZonesAndTheirUTC.put("GMT", 0);
        supportedZonesAndTheirUTC.put("PDT", -7);
    }

    public static void chceckIfSupported(String zoneName) {
        if(!supportedZonesAndTheirUTC.containsKey(zoneName)) {
            throw new IllegalArgumentException(zoneName + " time zone is not supported");
        }
    }

    public static Integer getUTCDifference(String zoneName) {
        return supportedZonesAndTheirUTC.get(zoneName);
    }
}