package com.example.bai5;

import android.os.Bundle;
import android.preference.PreferenceActivity;
@SuppressWarnings("deprecation")
public class MySettingsActivity
        extends PreferenceActivity {
    public void onCreate
            (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource
                (R.xml.settings);
    }
}