package com.deepakbaliga.getdone.model;

import android.net.Uri;

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
}
