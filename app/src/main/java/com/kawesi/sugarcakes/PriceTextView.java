package com.kawesi.sugarcakes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.core.widget.TextViewCompat;

import java.text.NumberFormat;
import java.util.Locale;


@SuppressLint("AppCompatCustomView")
public class PriceTextView extends TextView {
    private static NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(new Locale("ne", "UG"));
    private float mPrice;

    public PriceTextView(Context context) {
        super(context);

    }

    public PriceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public PriceTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PriceTextView, defStyle, 0);
        if (a.hasValue(R.styleable.PriceTextView_price)) {
            setPrice(a.getFloat(R.styleable.PriceTextView_price,
                    0)); // default value
        }
        a.recycle();

    }



    public void setPrice(float price){
        mPrice = price;
        setText(CURRENCY_FORMAT.format(mPrice));
    }

    public float getPrice(){
        return mPrice;
    }
}


