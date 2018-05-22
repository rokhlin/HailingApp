package com.selfaps.hailingapp.utils;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;

import com.selfaps.hailingapp.R;
import com.selfaps.hailingapp.model.Stations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class Utils {
    private static final long HOUR = 3600000;
    private static final long LEFT_BOARDER = 0;
    private static final long RIGHT_BOARDER = 3*HOUR;

    private static final String[] stationNames ={"Castle",
                                    "Shekem",
                                    "Habima",
                                    "Gordon",
                                    "Azrieli",
                                    "Hadera",
                                    "Bat Yam",
                                    "Rishon"};

    private static final int[] stationIcons ={R.mipmap.castle,
                                R.mipmap.shekem,
                                R.mipmap.habima,
                                R.mipmap.gordon,
                                R.mipmap.azrieli,
                                R.mipmap.habima,
                                R.mipmap.batyam,
                                R.mipmap.rishon};

    public static String getTime(long eta) {
        SimpleDateFormat dateFormat;

        if (eta < HOUR)
            dateFormat = new SimpleDateFormat("m'm'", Locale.ENGLISH);
        else
            dateFormat = new SimpleDateFormat("h'h' m'm'", Locale.ENGLISH);

        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(new Date(eta));
    }

    public static Stations[] generateData() {
        ArrayList<Stations> stations = new ArrayList<>();

        for (int i = 0; i <stationNames.length ; i++) {
            stations.add(new Stations(stationIcons[i],stationNames[i],getRandomTime()));
        }

        return sort(stations.toArray());
    }

    public static Stations[] updateData(Stations[] stations) {

        for (int i = 0; i < stations.length ; i++) {
            stations[i].setEta(getRandomTime());
        }

        return stations;
    }


    private static Stations[] sort(@NonNull Object[] array) {
        Stations[] stations =  Arrays.copyOf(array, array.length, Stations[].class);
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
