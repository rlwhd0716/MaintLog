package com.github.util.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.github.util.R;

public class TextViewOutLine extends AppCompatTextView {

    private int strokeColor;
    private float strokeWidthVal;

    public TextViewOutLine(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OutLineTextView);
        this.strokeColor = typedArray.getColor(R.styleable.OutLineTextView_textStrokeColor, Color.WHITE);
        this.strokeWidthVal = typedArray.getFloat(R.styleable.OutLineTextView_textStrokeWidth, 3f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ColorStateList colorStateList = getTextColors();
        Paint paint = getPaint();

        // draw stroke
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidthVal);
        setTextColor(strokeColor);
        super.onDraw(canvas);

        // draw fill
        paint.setStyle(Paint.Style.FILL);
        setTextColor(colorStateList);
        super.onDraw(canvas);
    }
}
