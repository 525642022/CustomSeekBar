package com.example.customseekbar;

import android.content.res.Resources;
import android.util.TypedValue;

import java.math.BigDecimal;

/**
 * Created by ljc on 2017/4/2.
 */

public class CustomSeekBarUtils {
    static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    static int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    static String float2String(float value) {
        return String.valueOf(formatFloat(value));
    }

    static float formatFloat(float value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
