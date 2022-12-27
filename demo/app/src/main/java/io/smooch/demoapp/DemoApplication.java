package io.smooch.demoapp;

import android.app.Application;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Below is where you would put your app's android-sdk integrationId to initialize the Smooch class;
        // Find it on the Sunshine Conversations dashboard, or via API: https://docs.smooch.io/rest/#get-sdk-ids
    }

}
