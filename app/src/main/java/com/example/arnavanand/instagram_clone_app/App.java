package com.example.arnavanand.instagram_clone_app;
import com.parse.Parse;
import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("TvhZDpdWQxBseuyEWPrhRfKlmLaevSAP4kTV52rg")
                .clientKey("kcRDBkyptjqP1o0MHz2jV5VcwnAUGA8InySuBLAK")
                .server("https://parseapi.back4app.com")
                .build()
        );


    }
}
