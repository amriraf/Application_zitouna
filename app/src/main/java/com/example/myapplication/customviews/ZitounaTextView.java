package com.example.myapplication.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

public class ZitounaTextView extends AppCompatTextView {

    public ZitounaTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ZitounaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZitounaTextView(Context context) {
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
