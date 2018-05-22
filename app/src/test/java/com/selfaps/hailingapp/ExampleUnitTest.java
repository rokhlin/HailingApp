package com.selfaps.hailingapp;

import com.selfaps.hailingapp.utils.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public final static long HOUR = 3600000;
    public static final long MINUTE = 60000;

    @Test
    public void timeConvert() throws Exception {
        assertEquals("1h 0m", Utils.getFormatedTime(HOUR));
        assertEquals("1h 10m", Utils.getFormatedTime(HOUR + 10*MINUTE));
        assertEquals("50m", Utils.getFormatedTime(HOUR - 10*MINUTE));
        assertEquals("0m", Utils.getFormatedTime(10));

    }


}