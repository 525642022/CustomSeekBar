package com.example.customseekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import static com.example.customseekbar.CustomSeekBarUtils.dp2px;
import static com.example.customseekbar.CustomSeekBarUtils.float2String;

/**
 * Created by ljc on 2017/4/2.
 */

public class CustomVerticalSeekBar extends BaseCustomSeekBar {

    private float xVerticalLeft;//中线到左方方距离
    private Paint mPaint;
    private float mCenterX;
    private float mCenterY;
    private float yCenterMove;//x方向移动距离
    private float yVerticalTop;
    private float yVerticalBottom;
    private float xInterval;
    public CustomVerticalSeekBar(Context context) {
        super(context);
    }

    public CustomVerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 设置 mPaint默认属性
     */
    @Override
    public void setPaintDefault() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        yVerticalTop = getPaddingTop();
        yVerticalBottom = getMeasuredHeight() - getPaddingTop();
        yCenterMove = progress * (yVerticalBottom - yVerticalTop) / 100;
        xInterval = (yVerticalBottom - yVerticalTop) / mCount;
        showText(canvas);
        drawAllLine(canvas);
        drawThumb(canvas);
    }

    /***
     * 画拖动条
     *
     * @param canvas
     */
    private void drawThumb(Canvas canvas) {
        mPaint.setColor(mThumbColor);
        canvas.drawCircle(mCenterX, mCenterY, mThumbRadius, mPaint);
    }

    /***
     * 画所有线
     *
     * @param canvas
     */
    private void drawAllLine(Canvas canvas) {
        mPaint.setColor(mCompleteColor);
        mPaint.setStrokeWidth(dp2px(2));
        mCenterX =xVerticalLeft + mHeight / 2;
        mCenterY =yVerticalTop + yCenterMove;
        canvas.drawLine(xVerticalLeft, yVerticalTop, xVerticalLeft+mHeight, yVerticalTop, mPaint);
        canvas.drawLine(xVerticalLeft+mHeight/2, yVerticalTop, xVerticalLeft+mHeight/2, mCenterY, mPaint);
        mPaint.setColor(mInCompleteColor);
        canvas.drawLine(xVerticalLeft+mHeight/2, mCenterY, xVerticalLeft+mHeight/2, yVerticalBottom, mPaint);
        canvas.drawLine(xVerticalLeft, yVerticalBottom, xVerticalLeft+mHeight, yVerticalBottom, mPaint);
        for (int i = 0; i < mCount; i++) {
            if (yVerticalTop + (xInterval * i) < mCenterY) {
                mPaint.setColor(mCompleteColor);
            } else {
                mPaint.setColor(mInCompleteColor);
            }
            canvas.drawLine(xVerticalLeft, yVerticalTop+(xInterval * i), xVerticalLeft+mHeight, yVerticalTop+(xInterval * i), mPaint);
        }
    }

    /**
     * 画文字
     *
     * @param canvas
     */
    private void showText(Canvas canvas) {
        float xThumbText;
        float yThumbText;
        float xText;
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.getTextBounds(float2String(mMin), 0, float2String(mMin).length(), mRectText);
        if (mTextStyle == TextStyle.TOP_TEXT) {
            mPaint.setTextSize(mTextSize);
            mPaint.setColor(mTextColor);
            mPaint.getTextBounds(float2String(mMin+((mMax-mMin)  * progress / 100)), 0,float2String(mMin+(mMax-mMin)  * progress / 100).length(), mRectText);
            xThumbText= mTextSpace;
            yThumbText = yVerticalTop+yCenterMove +mRectText.height()/2;
            canvas.drawText(float2String(mMin+((mMax-mMin)  * progress / 100)), xThumbText, yThumbText, mPaint);
            mPaint.getTextBounds(float2String(mMin), 0, float2String(mMin).length(), mRectText);
            xVerticalLeft = 2*mTextSpace +  mRectText.width();
            xText = xVerticalLeft + mHeight / 2  + mTextSpace + mThumbRadius;
            canvas.drawText(float2String(mMin), xText, yVerticalTop + mRectText.height() / 2, mPaint);
            mPaint.getTextBounds(float2String(mMax), 0, float2String(mMax).length(), mRectText);
            canvas.drawText(float2String(mMax),xText , yVerticalBottom +mRectText.height() / 2, mPaint);
        } else {
            mPaint.getTextBounds(float2String(mMax), 0, float2String(mMax).length(), mRectText);
            xText =mTextSpace;
            canvas.drawText(float2String(mMin),xText , yVerticalTop + mRectText.height() / 2, mPaint);
            canvas.drawText(float2String(mMax),xText , yVerticalBottom +mRectText.height() / 2, mPaint);
            xVerticalLeft = 2 * mTextSpace + mRectText.width();
            mPaint.getTextBounds(float2String(mMax * progress / 100), 0, float2String(mMax * progress / 100).length(), mRectText);
            xThumbText=xVerticalLeft + mHeight / 2  + mTextSpace + mThumbRadius ;
            yThumbText =  yVerticalTop + yCenterMove + mRectText.height() / 2;
            mPaint.setTextSize(mTextSize);
            mPaint.setColor(mTextColor);
            mPaint.getTextBounds(float2String(mMin+(mMax-mMin)  * progress / 100), 0, float2String(mMin+(mMax-mMin)  * progress / 100).length(), mRectText);
            canvas.drawText(float2String(mMin+((mMax-mMin)  * progress / 100)), xThumbText, yThumbText, mPaint);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isThumb = isThumb(event);
                if (isDownMove) {
                    System.out.println();
                    progress = (getTouchY(event.getY()) - yVerticalTop) / (yVerticalBottom - yVerticalTop) * 100;
                    invalidate();
                } else if (isThumb) {
                    mThumbRadius = mThumbRadius * 3 / 2;
                    progress = (getTouchY(event.getY()) - yVerticalTop) / (yVerticalBottom - yVerticalTop) * 100;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDownMove) {
                    progress = (getTouchY(event.getY()) - yVerticalTop) / (yVerticalBottom - yVerticalTop) * 100;
                    invalidate();
                } else if (isThumb) {
                progress = (getTouchY(event.getY()) - yVerticalTop) / (yVerticalBottom - yVerticalTop) * 100;
                invalidate();
            }
                if(progressChangeListener!=null) progressChangeListener.progressChanging(progress);
                break;
            case MotionEvent.ACTION_UP:
                if (isThumb&&!isDownMove) {
                    mThumbRadius = mThumbRadius * 2 / 3;
                    invalidate();
                }
                if(progressChangeListener!=null) progressChangeListener.progressChangeDown(progress);
                break;
        }
        return true;
    }

    /**
     * 判断点击的是否时thumb
     *
     * @param event
     * @return
     */
    public boolean isThumb(MotionEvent event) {
        return (mCenterX - event.getX()) * (mCenterX - event.getX()) + (mCenterY - event.getY()) * (mCenterY - event.getY()) < 2.25*mThumbRadius * mThumbRadius;
    }

    /**
     * 将x轴点击坐标与两端对比
     *
     * @param y y轴坐标
     * @return 比较后数值
     */
    public float getTouchY(float y) {
        if (y < yVerticalTop) {
            return yVerticalTop;
        } else if (y > yVerticalBottom) {
            return yVerticalBottom;
        } else {
            return y;
        }
    }
}
