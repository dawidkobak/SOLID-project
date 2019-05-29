package test;

import main.TimeAndZoneKeeper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TimeAndZoneKeeperTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenTimeIsIncorrect(){
        String time = "24:41:05";
        String zoneToBeConverted = "PDT";

        TimeAndZoneKeeper timeAndZoneKeeper = new TimeAndZoneKeeper(time, zoneToBeConverted);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenTimeZoneUnsupported(){
        String time = "23:41:05";
        String zoneToBeConverted = "dangerZone";

        TimeAndZoneKeeper timeAndZoneKeeper = new TimeAndZoneKeeper(time, zoneToBeConverted);
    }
}
