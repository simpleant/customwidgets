package com.antwei.customwidgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Ant on 2015/11/13.
 */
public class RoundAngleImageView extends ImageView {

    private Paint paint;
    private Paint paint2;

    private int topLeftRadius = 5;
    private int topRightRadius = 5;
    private int btmLeftRadius = 5;
    private int btmRightRadius = 5;

    public RoundAngleImageView(Context context) {
        super(context);
        init(context, null);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {

        if (attributeSet != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RoundAngleImageView);
            topLeftRadius = typedArray.getDimensionPixelSize(R.styleable.RoundAngleImageView_topLeftRadius, topLeftRadius);
            topRightRadius = typedArray.getDimensionPixelSize(R.styleable.RoundAngleImageView_topRightRadius, topLeftRadius);
            btmLeftRadius = typedArray.getDimensionPixelOffset(R.styleable.RoundAngleImageView_btmLeftRadius, btmLeftRadius);
            btmRightRadius = typedArray.getDimensionPixelOffset(R.styleable.RoundAngleImageView_btmRightRadius, btmRightRadius);
        } else {
            float density = context.getResources().getDisplayMetrics().density;
            topLeftRadius = (int) (topLeftRadius * density);
            topRightRadius = (int) (topRightRadius * density);
            btmLeftRadius = (int) (btmLeftRadius * density);
            btmRightRadius = (int) (btmRightRadius * density);
        }


        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(bitmap);
        super.onDraw(mCanvas);

        drawTopLeft(mCanvas);
        drawTopRight(mCanvas);
        drawBtmLeft(mCanvas);
        drawBtmRight(mCanvas);
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        bitmap.recycle();
    }

    private void drawTopLeft(Canvas canvas) {

        Path path = new Path();
        path.moveTo(0, topLeftRadius);
        path.lineTo(0, 0);
        path.lineTo(topLeftRadius, 0);
        path.arcTo(new RectF(
                        0,
                        0,
                        topLeftRadius * 2,
                        topLeftRadius * 2),
                -90,
                -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawTopRight(Canvas canvas) {

        Path path = new Path();
        path.moveTo(getWidth() - topRightRadius * 2, 0);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth(), topRightRadius * 2);

        path.arcTo(new RectF(getWidth() - topRightRadius * 2, 0, getWidth(), 0 + topRightRadius * 2), -0, -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawBtmLeft(Canvas canvas) {

        Path path = new Path();
        path.moveTo(0, getHeight() - btmLeftRadius);
        path.lineTo(0, getHeight());
        path.lineTo(btmLeftRadius*2, getHeight());

        path.arcTo(new RectF(0, getHeight() - btmLeftRadius * 2, 0 + btmLeftRadius * 2, getHeight()), 90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawBtmRight(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth() - btmLeftRadius * 2, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() - btmLeftRadius * 2);
        path.arcTo(new RectF(getWidth() - btmLeftRadius * 2, getHeight() - btmLeftRadius * 2, getWidth(), getHeight()), 0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

}
