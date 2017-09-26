package com.example.android.pynpoint;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by james on 9/26/2017.
 */


import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_app);
    }
}
