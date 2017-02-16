package com.deepakbaliga.getdone.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.deepakbaliga.getdone.utilities.FontCache;

/**
 * Created by deepakbaliga on 13/02/17.
 */

public class BoldTextView extends TextView {


    public BoldTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public BoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public BoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }


    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("sf_bold.otf", context);
        setTypeface(customFont);
    }

}
