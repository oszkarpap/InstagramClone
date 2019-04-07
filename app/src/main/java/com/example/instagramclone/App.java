package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DS9qLqspviawK0vF1BF1aC89Ice14xaIvzv1sKsX")
                // if defined
                .clientKey("SY83xZtt5UFIaKHQ60pwYE77eqWjYuvDNyIsEIck")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
