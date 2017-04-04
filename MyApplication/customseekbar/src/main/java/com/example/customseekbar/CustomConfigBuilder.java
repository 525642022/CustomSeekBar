package com.example.customseekbar;

import static com.example.customseekbar.CustomSeekBarUtils.dp2px;
import static com.example.customseekbar.CustomSeekBarUtils.sp2px;

/**
 * Created by ljc on 2017/4/3.
 */

public class CustomConfigBuilder {
    float mMin;//seekbar最小值
    float mMax;//seekbar最大值
    float mHeight;//默认条目高度
    float mThumbRadius;//中间拖动条半径
    int mCompleteColor;//已完成部分颜色
    int mInCompleteColor;//未完成部分颜色
    int mThumbColor;//拖动条颜色
    int mTextColor;//字颜色
    int mCount;//间隔数量
    float mTextSpace;//字与center具体
    float mTextSize;//字大小
    float progress;
    boolean isDownMove;//点击下去是否移动
    @BaseCustomSeekBar.TextStyle
    int mTextStyle;
    BaseCustomSeekBar mCustomBar;

    CustomConfigBuilder(BaseCustomSeekBar customSeekBar) {
        mCustomBar = customSeekBar;
    }

    public void build() {
        mCustomBar.config(this);
    }

    public CustomConfigBuilder mMin(float mMin) {
        this.mMin = mMin;
        return this;
    }

    public CustomConfigBuilder mMax(float mMax) {
        this.mMax = mMax;
        return this;
    }

    public CustomConfigBuilder mHeight(int dp) {
        this.mHeight = dp2px(dp);
        return this;
    }

    public CustomConfigBuilder mThumbRadius(int dp) {
        this.mThumbRadius =  dp2px(dp);
        return this;
    }

    public CustomConfigBuilder mCompleteColor(int mCompleteColor) {
        this.mCompleteColor = mCompleteColor;
        return this;
    }

    public CustomConfigBuilder mInCompleteColor(int mInCompleteColor) {
        this.mInCompleteColor = mInCompleteColor;
        return this;
    }

    public CustomConfigBuilder mThumbColor(int mThumbColor) {
        this.mThumbColor = mThumbColor;
        return this;
    }

    public CustomConfigBuilder mTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        return this;
    }

    public CustomConfigBuilder mCount(int mCount) {
        this.mCount = mCount;
        return this;
    }
    public CustomConfigBuilder progress(float progress) {
        this.progress = progress;
        return this;
    }
    public CustomConfigBuilder mTextSpace(int dp) {
        this.mTextSpace =  dp2px(dp);
        return this;
    }

    public CustomConfigBuilder mTextSize(int sp) {
        this.mTextSize =  sp2px(sp);
        return this;
    }

    public CustomConfigBuilder isDownMove(boolean isDownMove) {
        this.isDownMove = isDownMove;
        return this;
    }

    public CustomConfigBuilder mTextStyle(int mTextStyle) {
        this.mTextStyle = mTextStyle;
        return this;
    }

    public float getmMin() {
        return mMin;
    }

    public float getmMax() {
        return mMax;
    }

    public float getmHeight() {
        return mHeight;
    }

    public float getmThumbRadius() {
        return mThumbRadius;
    }

    public int getmCompleteColor() {
        return mCompleteColor;
    }

    public int getmInCompleteColor() {
        return mInCompleteColor;
    }

    public int getmThumbColor() {
        return mThumbColor;
    }

    public int getmTextColor() {
        return mTextColor;
    }

    public int getmCount() {
        return mCount;
    }

    public float getmTextSpace() {
        return mTextSpace;
    }

    public float getmTextSize() {
        return mTextSize;
    }

    public boolean isDownMove() {
        return isDownMove;
    }
    public int getmTextStyle() {
        return mTextStyle;
    }

    public float getProgress() {
        return progress;
    }

    public BaseCustomSeekBar getmCustomBar() {
        return mCustomBar;
    }
}
