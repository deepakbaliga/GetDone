package com.deepakbaliga.getdone.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.deepakbaliga.getdone.utilities.FontCache;

/**
 * Created by deepakbaliga on 13/02/17.
 */

public class ThinEditTextView extends EditText {


    public ThinEditTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public ThinEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public ThinEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }


    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("sf_light.otf", context);
        setTypeface(customFont);
    }

    @Override
    public boolean isInEditMode() {
        return  true;
    }
}
