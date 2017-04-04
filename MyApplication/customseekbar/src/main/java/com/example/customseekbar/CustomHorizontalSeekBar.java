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

public class CustomHorizontalSeekBar extends BaseCustomSeekBar {

    private float xTop;//中线到上方距离
    private Paint mPaint;
    private float mCenterX;
    private float mCenterY;
    private float xCenterMove;//x方向移动距离
    private float xLeft;
    private float xRight;
    private float xInterval;
    public CustomHorizontalSeekBar(Context context) {
        super(context);
    }

    public CustomHorizontalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHorizontalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
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
        xLeft = getPaddingLeft();
        xRight = getMeasuredWidth() - getPaddingRight();
        xCenterMove = progress * (xRight - xLeft) / 100;
        xInterval = (xRight - xLeft) / mCount;
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
        mCenterX = xLeft + xCenterMove;
        mCenterY = xTop + mHeight / 2;
        canvas.drawLine(xLeft, xTop, xLeft, mHeight + xTop, mPaint);
        canvas.drawLine(xLeft, xTop + mHeight / 2, mCenterX, xTop + mHeight / 2, mPaint);
        mPaint.setColor(mInCompleteColor);
        canvas.drawLine(mCenterX, xTop + mHeight / 2, xRight, xTop + mHeight / 2, mPaint);
        canvas.drawLine(xRight, xTop + 0, xRight, xTop + mHeight, mPaint);
        for (int i = 0; i < mCount; i++) {
            if (xLeft + (xInterval * i) < mCenterX) {
                mPaint.setColor(mCompleteColor);
            } else {
                mPaint.setColor(mInCompleteColor);
            }
            canvas.drawLine(xLeft + (xInterval * i), xTop, xLeft + (xInterval * i), mHeight + xTop, mPaint);
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
        float yText;
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.getTextBounds(float2String(mMin), 0, float2String(mMin).length(), mRectText);
        if (mTextStyle == TextStyle.TOP_TEXT) {
            mPaint.setTextSize(mTextSize);
            mPaint.setColor(mTextColor);
            mPaint.getTextBounds(float2String(mMin+(mMax * progress / 100)), 0, float2String(mMin+(mMax * progress / 100)).length(), mRectText);
            xThumbText = xLeft + xCenterMove - mRectText.width() / 2;
            yThumbText = mTextSpace + mRectText.height();
            canvas.drawText(float2String(mMin+(mMax * progress / 100)), xThumbText, yThumbText, mPaint);
            mPaint.getTextBounds(float2String(mMin), 0, float2String(mMin).length(), mRectText);
            xTop = 2*mTextSpace + mRectText.height();
            yText = xTop + mRectText.height() + mTextSpace + 2*mThumbRadius;
            canvas.drawText(float2String(mMin), xLeft - mRectText.width() / 2, yText, mPaint);
            mPaint.getTextBounds(float2String(mMax), 0, float2String(mMax).length(), mRectText);
            canvas.drawText(float2String(mMax) + "", xRight - mRectText.width() / 2, yText, mPaint);
        } else {
            mPaint.getTextBounds(float2String(mMin), 0, float2String(mMin).length(), mRectText);
            yText = mTextSpace + mRectText.height();
            canvas.drawText(float2String(mMin), xLeft - mRectText.width() / 2, yText, mPaint);
            mPaint.getTextBounds(float2String(mMax), 0, float2String(mMax).length(), mRectText);
            canvas.drawText(float2String(mMax) + "", xRight - mRectText.width() / 2, yText, mPaint);
            mPaint.getTextBounds(float2String(mMax * progress / 100), 0, float2String(mMax * progress / 100).length(), mRectText);
            xTop =2* mTextSpace +  mRectText.height();
            xThumbText = xLeft + xCenterMove - mRectText.width() / 2;
            yThumbText = xTop  + mRectText.height() + mTextSpace + 2*mThumbRadius;
            mPaint.setTextSize(mTextSize);
            mPaint.setColor(mTextColor);
            mPaint.getTextBounds(float2String(mMin+(mMax * progress / 100)), 0,float2String(mMin+(mMax * progress / 100)).length(), mRectText);
            canvas.drawText(float2String(mMin+(mMax * progress / 100)), xThumbText, yThumbText, mPaint);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isThumb = isThumb(event);
                if (isDownMove) {
                    progress = (getTouchX(event.getX()) - xLeft) / (xRight - xLeft) * 100;
                    invalidate();
                } else if (isThumb) {
                    mThumbRadius = mThumbRadius * 3 / 2;
                    progress = (getTouchX(event.getX()) - xLeft) / (xRight - xLeft) * 100;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDownMove) {
                    progress = (getTouchX(event.getX()) - xLeft) / (xRight - xLeft) * 100;
                    invalidate();
                } else if (isThumb) {
                    progress = (getTouchX(event.getX()) - xLeft) / (xRight - xLeft) * 100;
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
     * @param x x轴坐标
     * @return 比较后数值
     */
    public float getTouchX(float x) {
        if (x < xLeft) {
            return xLeft;
        } else if (x > xRight) {
            return xRight;
        } else {
            return x;
        }
    }
}
