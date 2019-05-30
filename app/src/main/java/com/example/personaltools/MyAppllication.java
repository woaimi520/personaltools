package com.example.personaltools;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MyAppllication extends Application {
    private RefWatcher refWatcher;
    private static Context instance;

    public static RefWatcher getRefWatcher(Context context) {
        MyAppllication application = (MyAppllication) context.getApplicationContext();
        return application.refWatcher;
    }
    public static Context getContext()
    {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher=LeakCanary.install(this);
        // Normal app init code...

    }
}
