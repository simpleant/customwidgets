package com.antwei.customwidgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ant on 15-5-5.
 */
public class AutoHightImageView extends ImageView {

    private Bitmap mBitmap;

    public AutoHightImageView(Context context) {
        super(context);
    }

    public AutoHightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoHightImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 在使用时设置scaletype 为fitxy,
     *设置adjustViewBounds = true
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mBitmap != null) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
//            int height = width * mBitmap.getHeight() / mBitmap.getWidth();
            int height = (int) Math.ceil(width*mBitmap.getHeight()/mBitmap.getWidth());
            setMeasuredDimension(width, height);

        } else {
            super.onMeasure(widthMeasureSpec,
                    heightMeasureSpec);
        }
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
        super.setImageBitmap(bitmap);
    }
}
