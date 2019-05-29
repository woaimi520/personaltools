package com.example.personaltools;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class Myappllication extends Application {
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        Myappllication application = (Myappllication) context.getApplicationContext();
        return application.refWatcher;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher=LeakCanary.install(this);
        // Normal app init code...

    }
}
