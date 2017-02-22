package com.deepakbaliga.getdone;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by deepakbaliga on 22/02/17.
 */

public class GetDone extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
