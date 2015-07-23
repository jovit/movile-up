package com.movile.up.seriestracker.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.movile.up.seriestracker.R;


public class RoundedFrameLayout extends FrameLayout {

    private Path mPath;
    private float mCornerRadius;

    public RoundedFrameLayout(Context context) {
        super(context);
        initialize(context, null, 0);
    }

    public RoundedFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0);
    }

    public RoundedFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context, attrs, defStyle);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RoundedFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundedFrameLayout, defStyleAttr, 0);
        mCornerRadius = a.getDimension(R.styleable.RoundedFrameLayout_rfl_cornerRadius, 0.0f);
        a.recycle();

        if (getBackground() == null) {
            setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.clipPath(mPath);
        super.draw(canvas);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        RectF r = new RectF(0, 0, w, h);
        mPath = new Path();
        mPath.addRoundRect(r, mCornerRadius, mCornerRadius, Path.Direction.CW);
        mPath.close();
    }

    public void setCornerRadius(int radius) {
        mCornerRadius = radius;
        invalidate();
    }
}
