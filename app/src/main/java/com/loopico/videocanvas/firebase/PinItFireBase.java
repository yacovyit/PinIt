package com.loopico.videocanvas.firebase;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.loopico.videocanvas.pinitclasses.Cursor;

import java.util.List;

/**
 * Created by yacovyitzhak on 20/03/2016.
 */
public class PinItFireBase {

    private static final String child = "cursor";


    //url FIREBASE_URL = "https://wizardapp.firebaseio.com/camera_ip/{screen_name}/cursor";
    public static <T extends Cursor> void addChildEventListener(String url,final List<T> list,final Class<T> clazz){
        new Firebase(url)
                .addChildEventListener(new ChildEventListener() {
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        list.add((T) dataSnapshot.child(child).getValue(clazz));
                    }

                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        list.remove((String) dataSnapshot.child(child).getValue());
                    }

                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });

    }
    public static<T extends Cursor> void add(String url,T item){
        new Firebase(url + item.getAction())
                .push()
                .child(child)
                .setValue(item);
    }

}
