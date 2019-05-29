package com.example.personaltools;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

public class PersonInfoObserver  extends ContentObserver {
    private static final String TAG = "PersonInfoObserver";

    public PersonInfoObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.d(TAG, "receive change");
    }
}
