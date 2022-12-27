package io.smooch.demoapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.smooch.core.InitializationStatus;
import io.smooch.core.Settings;
import io.smooch.core.Smooch;
import io.smooch.core.SmoochCallback;
import io.smooch.core.User;
import io.smooch.features.conversationlist.ConversationListActivity;
import io.smooch.ui.ConversationActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    // Hey App Makers, Smooch here, there isn't
    // really nothing to see here, we have integrated
    // Smooch APIs in the fragments.

    private NavigationDrawerFragment navigationDrawerFragment;

    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Smooch.init(getApplication(), new Settings("59a93c829200175800018222"), new SmoochCallback<InitializationStatus>() {
            @Override
            public void run(Response response) {
                // Handle init response here!
                Log.d("MainActivity", "Running Smooch... Vrooom!" + response.toString());
            }
        });
        addSomeProperties(User.getCurrentUser());

        setContentView(R.layout.activity_main);

        navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        title = getTitle();

        navigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {
            case 0:
                IntroductionFragment introductionFragment = new IntroductionFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, introductionFragment)
                        .commit();
                break;
            case 1:
                ConversationListActivity.builder().show(this);
                break;
            case 2:
                ConversationActivity.builder().show(this);
                break;
            case 3:
                SettingsFragment settingsFragment = new SettingsFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, settingsFragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                title = getString(R.string.title_section1);
                break;
            case 2:
                title = getString(R.string.title_section2);
                break;
            case 3:
                title = getString(R.string.title_section3);
                break;
            case 4:
                title = getString(R.string.title_section4);
                break;
        }
    }

    void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }


    private void addSomeProperties(final User user) {
        final Map<String, Object> customProperties = new HashMap<>();

        // Identify user with default properties
        user.setFirstName("Demo");
        user.setLastName("App");
        user.setEmail("demo.app@smooch.io");
        user.setSignedUpAt(new Date(1420070400000L));

        // Add your own custom properties
        customProperties.put("Last Seen", new Date());
        customProperties.put("Awesome", true);
        customProperties.put("Karma", 1337);
        user.addMetadata(customProperties);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
}
