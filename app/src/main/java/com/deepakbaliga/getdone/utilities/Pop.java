package com.deepakbaliga.getdone.utilities;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.customViews.BoldTextView;

import java.util.zip.Inflater;

/**
 * Created by deepakbaliga on 20/02/17.
 */

public class Pop {

    private static Context context;
    private static String string;


    public static void  show(Context con, String str){

        context = con;
        string = str;


        Activity activity = (Activity) context;

        View layout = LayoutInflater.from(context).inflate(R.layout.get_done_toast,(ViewGroup) activity.findViewById(R.id.custom_toast) );

        BoldTextView textView = (BoldTextView)layout.findViewById(R.id.toast_message);
        textView.setText(string);
        android.widget.Toast toast = new android.widget.Toast(context);
        toast.setDuration(android.widget.Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
}
