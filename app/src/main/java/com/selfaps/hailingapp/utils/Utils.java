package com.selfaps.hailingapp.utils;

import android.support.annotation.NonNull;

import com.selfaps.hailingapp.R;
import com.selfaps.hailingapp.model.Stations;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class Utils {
    private static final long HOUR = 3600000;
    private static final long LEFT_BOARDER = 0;
    private static final long RIGHT_BOARDER = 3*HOUR;

    //Station names dataset for generating
    private static final String[] stationNames ={"Castle",
                                    "Shekem",
                                    "Habima",
                                    "Gordon",
                                    "Azrieli",
                                    "Hadera",
                                    "Bat Yam",
                                    "Rishon"};
    //Icons dataset for generating
    private static final int[] stationIcons ={R.mipmap.castle,
                                R.mipmap.shekem,
                                R.mipmap.habima,
                                R.mipmap.gordon,
                                R.mipmap.azrieli,
                                R.mipmap.habima,
                                R.mipmap.batyam,
                                R.mipmap.rishon};

    /**
     *
     * @param eta long value
     * @return Formated string
     */
    public static String getFormatedTime(long eta) {
        SimpleDateFormat dateFormat;

        if (eta < HOUR)
            dateFormat = new SimpleDateFormat("m'm'", Locale.ENGLISH);
        else
            dateFormat = new SimpleDateFormat("h'h' m'm'", Locale.ENGLISH);

        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(new Date(eta));
    }

    /**
     * Test data generator
     * @return array of stations
     */
    public static  Stations[] generateData() {
        Stations[] stations = new Stations[stationNames.length];

        for (int i = 0; i <stationNames.length ; i++) {
            stations[i] = new Stations(stationIcons[i],stationNames[i],getRandomTime());
        }

        return stations;
    }

    /**
     * Array sorting using ETA value
     * @param stations Unsorted array
     * @return sorted array
     */
    public static Stations[] sort(@NonNull Stations[] stations) {
        Arrays.sort(stations, new Comparator<Stations>() {
            @Override
            public int compare(Stations o1, Stations o2) {
                return o1.getEta().compareTo(o2.getEta()) ;
            }
        });
        return stations;
    }


    private static Long getRandomTime() {
        return LEFT_BOARDER + (long) (Math.random() * RIGHT_BOARDER);
    }
}
