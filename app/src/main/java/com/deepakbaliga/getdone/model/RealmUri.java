package com.deepakbaliga.getdone.model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by deepakbaliga on 22/02/17.
 */

public class RealmUri extends RealmObject {

    private String path;
    private String encodedPath;


    public RealmUri() {
    }

    public RealmUri(String path, String encodedPath) {
        this.path = path;
        this.encodedPath = encodedPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEncodedPath() {
        return encodedPath;
    }

    public void setEncodedPath(String encodedPath) {
        this.encodedPath = encodedPath;
    }




    public static RealmList<RealmUri> getRealList(Realm realm, ArrayList<Uri> realmUris){

        RealmList<RealmUri> mRealmList = new RealmList<>();

        for(int i=0; i<realmUris.size(); i++){

            RealmUri realmUri = realm.createObject(RealmUri.class);
            realmUri.setEncodedPath(realmUris.get(i).getEncodedPath());
            realmUri.setPath(realmUris.get(i).getPath());

            mRealmList.add(realmUri);

        }

        return mRealmList;



    }
}
