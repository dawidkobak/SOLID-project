package test;

import main.TimeAndZoneConverter;
import main.TimeAndZoneKeeper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TimeAndZoneConverterTest {
    @Test
    public void shouldConvertTimeFromPdtToGmt() {
        String time = "23:41:05";
        String zoneToBeConverted = "PDT";
        String zoneAfterConversion = "GMT";

        TimeAndZoneKeeper timeAndZoneKeeper = new TimeAndZoneKeeper(time, zoneToBeConverted);
        TimeAndZoneConverter timeAndZoneConverter = new TimeAndZoneConverter(timeAndZoneKeeper, zoneAfterConversion);

        String expectedTime = "06:41:05";
        Assert.assertEquals(expectedTime, timeAndZoneConverter.convert());
    }

    @Test
    public void shouldConvertFromGmtToPdt() {
        String time = "06:41:05";
        String zoneToBeConverted = "GMT";
        String zoneAfterConversion = "PDT";

        TimeAndZoneKeeper timeAndZoneKeeper = new TimeAndZoneKeeper(time, zoneToBeConverted);
        TimeAndZoneConverter timeAndZoneConverter = new TimeAndZoneConverter(timeAndZoneKeeper, zoneAfterConversion);

        String expectedTime = "23:41:05";
        Assert.assertEquals(expectedTime, timeAndZoneConverter.convert());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenTimeZoneUnsupported(){
        String time = "23:41:05";
        String zoneToBeConverted = "PDT";
        String zoneAfterConversion = "dangerZone";

        TimeAndZoneKeeper timeAndZoneKeeper = new TimeAndZoneKeeper(time, zoneToBeConverted);
        TimeAndZoneConverter timeAndZoneConverter = new TimeAndZoneConverter(timeAndZoneKeeper, zoneAfterConversion);
    }
} 