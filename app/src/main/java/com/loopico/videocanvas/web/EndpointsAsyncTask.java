package com.loopico.videocanvas.web;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;


import com.example.yacovyitzhak.myapplication.backend.wizardApi.WizardApi;
import com.example.yacovyitzhak.myapplication.backend.wizardApi.model.CursorBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.loopico.videocanvas.pinitclasses.Cursor;
import com.loopico.videocanvas.app.Globals;
import com.loopico.videocanvas.enums.Origin;
import com.loopico.videocanvas.pinitclasses.Manager;

import java.io.IOException;
import java.util.List;

/**
 * Created by yacovyitzhak on 16/03/2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, Cursor>, Void, CursorBean> {


    private static WizardApi myApiService = null;
    private Context context;
    private Cursor srcCursor = null;
    public EndpointsAsyncTask(){
    }
    @Override
    protected CursorBean doInBackground(Pair<Context, Cursor>... params) {
        if(myApiService == null) {  // Only do this once
            WizardApi.Builder builder = new WizardApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(Globals.wizardAdiUrl)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        srcCursor = params[0].second;

        try {
            if (context!=null && srcCursor!=null){
                return myApiService.cursorLocationMagic(srcCursor.getX(),srcCursor.getY()).execute();
            }

        } catch (IOException e) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(CursorBean result) {
        if (result!=null){
                Cursor wizardCursor = new Cursor(result.getXWizzard(),result.getYWizzard(),srcCursor.getLayerColor(), Origin.WIZARD,srcCursor.getId(),srcCursor.getScreenName());
                wizardCursor.setFireBaseSrcClientId(srcCursor.getFireBaseClientId());
                Manager.Instance().remove(srcCursor);
                Manager.Instance().add(wizardCursor);
        }
    }
}