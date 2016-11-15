package com.example.itai.gabayapp.Prayers;

import java.util.List;

/**
 * Created by itai on 15/11/16.
 */

public class PrayersManager {

    List<Prayer> mPrayerList = null;

    static PrayersManager pManager = null;

    public PrayersManager instance() {
        if (pManager == null) {
            pManager = new PrayersManager();
        }
        return pManager;
    }

    public static class Prayer {
        String name;

        int rank;

        int type;

    }
}
