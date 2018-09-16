package com.quran.study.util;

import android.view.animation.AlphaAnimation;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class OpacityUtil {
    public AlphaAnimation set(float fromAlpha, float toAlpha) {
        AlphaAnimation alpha = new AlphaAnimation(fromAlpha, toAlpha);
        alpha.setDuration(0); // Make animation instant
        alpha.setFillAfter(true);

        return alpha;
    }
}
