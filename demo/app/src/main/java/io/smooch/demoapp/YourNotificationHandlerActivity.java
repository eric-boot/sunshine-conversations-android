package io.smooch.demoapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import io.smooch.core.InitializationStatus;
import io.smooch.core.Settings;
import io.smooch.core.Smooch;
import io.smooch.core.SmoochCallback;
import io.smooch.ui.ConversationActivity;

public class YourNotificationHandlerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("YourNotificationHandler", "Created activity!");
        Smooch.init(getApplication(), new Settings("59a93c829200175800018222s"), new SmoochCallback<InitializationStatus>() {
            @Override
            public void run(@NonNull Response<InitializationStatus> response) {
                Log.d("YourNotificationHandler", "Callback from Smooch");

                if (response.getData() == InitializationStatus.SUCCESS) {
                    ConversationActivity.builder().show(getApplicationContext());
                } else {
                    // Something went wrong during initialization
                }
            }
        });
    }
}
