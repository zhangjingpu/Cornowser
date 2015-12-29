package io.xdevs23.cornowser.browser;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import org.xdevs23.debugutils.Logging;

import io.fabric.sdk.android.Fabric;

public class Core extends Application {

    @Override
    public void onCreate() {
        Logging.logd("PRE INIT START");
        super.onCreate();

        Logging.logd("Initializing Crashlytics");
        Fabric.with(this, new Crashlytics());
    }

}