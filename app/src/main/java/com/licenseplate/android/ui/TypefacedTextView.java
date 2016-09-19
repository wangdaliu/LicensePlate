package com.licenseplate.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.licenseplate.android.R;

public class TypefacedTextView extends TextView {
    static final String TAG = "TypefacedTextView";

    public TypefacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Typeface.createFromAsset doesn't work in the layout editor. Skipping...
//        if (isInEditMode()) {
//            return;
//        }
//
//        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
//        String fontName = styledAttrs.getString(R.styleable.TypefacedTextView_typeface);
//        styledAttrs.recycle();
//
//        if (fontName != null) {
//            // Potential exception on some devices here due to Android bug; catch and ignore,
//            // which will mean uglier font on a small number of devices, but no crashes
//            try {
//                Typeface typeface =
//                        Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName + ".ttf");
//                setTypeface(typeface);
//            } catch (Exception e) {
//                Log.e(TAG, "couldn't load typeface " + fontName, e);
//            }
//        }
    }

}
