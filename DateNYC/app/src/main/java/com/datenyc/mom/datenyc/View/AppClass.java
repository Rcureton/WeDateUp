package com.datenyc.mom.datenyc.View;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Ra on 9/12/16.
 */
public class AppClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
