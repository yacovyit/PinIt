package com.loopico.videocanvas;

import android.app.Application;

import com.firebase.client.Firebase;
import com.loopico.videocanvas.app.AppSingleton;

/**
 * Created by yacovyitzhak on 20/03/2016.
 */
public class PinItApplication extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();

        // Initialize the singletons so their instances
        // are bound to the application process.
        Firebase.setAndroidContext(this);

        initSingletons();
    }

    protected void initSingletons()
    {
        // Initialize the instance of MySingleton
        AppSingleton.getInstance();
    }

    public void customAppMethod()
    {
        // Custom application method
    }
}
