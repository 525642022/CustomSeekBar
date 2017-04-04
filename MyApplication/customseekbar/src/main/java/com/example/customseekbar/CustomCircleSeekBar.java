package com.example.customseekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import static com.example.customseekbar.CustomSeekBarUtils.dp2px;
import static com.example.customseekbar.CustomSeekBarUtils.float2String;

/**
 * Created by ljc on 2017/4/4.
 */

public class CustomCircleSeekBar extends BaseCustomSeekBar {
    private Paint mPaint;
    private float mCircleLeft;
    private float mCircleTop;
    private float mCircleRight;
    private float mCircleBottom;
    private float radium;
    private float mXCenter;
    private float mYCenter;
    private float mMoveCircle;
    private float mXThumbCenter;
    private float mYThumbCenter;
    private boolean isCanMove;
    public CustomCircleSeekBar(Context context) {
        super(context);
    }

    public CustomCircleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCircleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setPaintDefault() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mMoveCircle=progress*360;
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        mCircleLeft = getPaddingLeft();
        mCircleTop = getPaddingTop();
        mCircleRight = getPaddingRight();
        mCircleBottom = getPaddingBottom();
        radium = getRadium(mCircleLeft, mCircleTop, mCircleRight, mCircleBottom);
        mXCenter = mCircleLeft + radium;
        mYCenter = mCircleTop + radium;
        RectF outRect = new RectF(mXCenter - radium, mYCenter - radium, mXCenter
                + radium, mYCenter + radium);
        RectF inRect = new RectF(mXCenter - radium+mHeight, mYCenter - radium+mHeight, mXCenter
                + radium-mHeight, mYCenter + radium-mHeight);
        RectF midRect = new RectF(mXCenter - radium+mHeight/2, mYCenter - radium+mHeight/2, mXCenter
                + radium-mHeight/2, mYCenter + radium-mHeight/2);
        mPaint.setColor(mCompleteColor);
        mPaint.setStrokeWidth(mHeight);
        canvas.drawArc(midRect,0, mMoveCircle, false, mPaint);
        mPaint.setStrokeWidth(1);
        canvas.drawArc(outRect,0, mMoveCircle, false, mPaint);
        canvas.drawArc(inRect,0, mMoveCircle, false, mPaint);
        mPaint.setColor(mInCompleteColor);
        canvas.drawArc(outRect,mMoveCircle,360-mMoveCircle, false, mPaint);
        canvas.drawArc(inRect,mMoveCircle, 360-mMoveCircle, false, mPaint);
        mPaint.setStrokeWidth(mHeight);
        canvas.drawArc(midRect,mMoveCircle, 360-mMoveCircle, false, mPaint);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(mThumbColor);
        mPaint.setStyle(Paint.Style.FILL);
        mXThumbCenter=(float)( mXCenter+(radium-mHeight/2)*Math.cos(mMoveCircle*Math.PI/180));
        mYThumbCenter=(float)( mYCenter+(radium-mHeight/2)*Math.sin(mMoveCircle*Math.PI/180));
        canvas.drawCircle(mXThumbCenter,mYThumbCenter,mThumbRadius,mPaint);
        mPaint.getTextBounds(float2String(progress), 0, float2String(progress).length(), mRectText);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        canvas.drawText(float2String(progress*100)+"%",mXCenter-mRectText.width()/2,mYCenter+mRectText.height()/2,mPaint);

    }

    private float getRadium(float mCircleLeft, float mCircleTop, float mCircleRight, float mCircleBottom) {
        float xRadium = (getMeasuredWidth() - mCircleLeft - mCircleRight) / 2;
        float yRadium = (getMeasuredHeight() - mCircleTop - mCircleBottom) / 2;
        return xRadium >= yRadium ? yRadium : xRadium;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isCanMove=isOutAndIn(event);
                isThumb = isThumb(event);
                System.out.println(isThumb);
                if (isDownMove&&isCanMove) {
                   progress= getProgress(event)/360;
                    invalidate();
                } else if (isThumb) {
                    progress= getProgress(event)/360;
                    mThumbRadius = mThumbRadius * 3 / 2;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDownMove&&isCanMove) {
                    progress= getProgress(event)/360;
                    invalidate();
                } else if (isThumb) {
                    progress= getProgress(event)/360;
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

    private float getProgress(MotionEvent event) {
        float x=event.getX()-mXCenter;
        float y=event.getY()-mYCenter;
        System.out.println(Math.toDegrees(Math.atan(y/x)));
        if(x>0&&y>0){
            return (float) Math.toDegrees(Math.atan(y/x));
        }else if(x<0&&y>0){
            return 180+(float) Math.toDegrees(Math.atan(y/x));
        }else if(x<0&&y<0){
            return 180+(float) Math.toDegrees(Math.atan(y/x));
        }else{
            return 360+(float) Math.toDegrees(Math.atan(y/x));
        }

    }

    /**
     * 设置有效点击区域
     * @param event
     * @return
     */
    private boolean isOutAndIn(MotionEvent event) {
         float x= event.getX()-mXCenter;
         float y= event.getY()-mYCenter;
        return x*x+y*y>(radium-2*mHeight)*(radium-2*mHeight)&& x*x+y*y<(radium+mHeight)*(radium+mHeight);
    }
    /**
     * 判断点击的是否时thumb
     *
     * @param event
     * @return
     */
    public boolean isThumb(MotionEvent event) {
        return (mXThumbCenter - event.getX()) * (mXThumbCenter - event.getX()) + (mYThumbCenter - event.getY()) * (mYThumbCenter - event.getY()) < 2.25*mThumbRadius * mThumbRadius;
    }
}
