package com.example.myapplication.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputEditText;

public class ZitounaEditText extends TextInputEditText {
    public ZitounaEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ZitounaEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZitounaEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/circular_std_book.otf");
            setTypeface(tf);
        }
    }
}
