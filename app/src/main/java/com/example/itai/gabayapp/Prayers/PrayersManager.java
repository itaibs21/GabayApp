package com.example.itai.gabayapp.Prayers;

/**
 * Created by itai on 15/11/16.
 */

public class PrayersManager {

    static PrayersManager pManager = null;

    public PrayersManager instance() {
        if (pManager == null) {
            pManager = new PrayersManager();
        }
        return pManager;
    }

    
}
