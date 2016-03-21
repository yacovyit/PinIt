package com.loopico.videocanvas.app;

import android.graphics.Bitmap;

import com.loopico.videocanvas.web.PinItFireBase;

/**
 * Created by yacovyitzhak on 20/03/2016.
 */
public class AppSingleton {
    private static AppSingleton ourInstance = new AppSingleton();



    private Bitmap targetIcon;
    private Bitmap wizardIcon;

    //private Firebase myFirebaseRef;
    private PinItFireBase pinItFireBase ;

    public static AppSingleton getInstance() {
        return ourInstance;
    }

    private AppSingleton() {
        //myFirebaseRef = new Firebase("https://<YOUR-FIREBASE-APP>.firebaseio.com/");
    }

    public Bitmap getTargetIcon() {
        return targetIcon;
    }

    public void setTargetIcon(Bitmap targetIcon) {
        ourInstance.targetIcon = targetIcon;
    }

    public Bitmap getWizardIcon() {
        return wizardIcon;
    }

    public void setWizardIcon(Bitmap wizardIcon) {
        ourInstance.wizardIcon = wizardIcon;
    }
}
