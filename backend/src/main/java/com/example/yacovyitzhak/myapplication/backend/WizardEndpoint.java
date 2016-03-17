/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.yacovyitzhak.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "wizardApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.yacovyitzhak.example.com",
    ownerName = "backend.myapplication.yacovyitzhak.example.com",
    packagePath=""
  )
)
public class WizardEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }
    @ApiMethod(name = "cursorLocationMagic")
    public CursorBean cursorLocationMagic(@Named("x") Integer x,@Named("y") Integer y) {
        CursorBean response = new CursorBean();
        response.setX(x);
        response.setX(y);
        response.setxWizzard(x + 100);
        response.setyWizzard(y + 100);
        return response;
    }
    @ApiMethod(name = "cursorLocationRandom")
    public CursorBean cursorLocationRandom(@Named("x") Integer x,@Named("y") Integer y) {
        CursorBean response = new CursorBean();
        response.setxWizzard(Utils.randInt(0, 500));
        response.setyWizzard(Utils.randInt(0, 500));
        return response;
    }

}
