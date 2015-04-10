package com.videodesk.eshopdemoapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Created by videodesk on 09/04/15.
 *
 * This is the mother class of all activities, it contains useful tools and attrs
 */
public class VdActivity extends Activity {
    boolean isMenuOpen = false;

    public void launchMenuAnimation(View v) {
        Log.d("MENU ANIM*******", "launchMenuAnimation");
        if(getIsMenuOpen() == true){
            //Log.d("isMenuOpen = ", ""+getIsMenuOpen());
            collapse(v);
            setIsMenuOpen(false);
        }
        else{
            //Log.d("isMenuOpen = ", ""+getIsMenuOpen());
            expand(v);
            setIsMenuOpen(true);
        }
    }

    /**
     * Expand the menu area by 75 dp.
     * @param v
     * the View to be expanded (typically slider menu)
     */

    public void expand(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = pxFromDp(this.getApplicationContext(), 75);

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);

        Animation a = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1 ? targetHeight : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(500);
        v.startAnimation(a);
    }

    /**
     * Collapse the slider menu and hide it
     * @param v
     * The View to be collapsed (typically slider menu)
     */
    public void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(500);
        v.startAnimation(a);
    }

    /**
     * Convert pixels into dp
     * @param context
     * @param px
     * @return int value converted into dp
     */
    public int dpFromPx(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().density);
    }

    /**
     * Convert dp into pixels
     * @param context
     * @param dp
     * @return int value converted into pixels
     */
    public int pxFromDp(final Context context, final float dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    /**
     * Set boolean value to true when slider menu is open
     * @param b boolean
     */
    protected void setIsMenuOpen(boolean b){
        this.isMenuOpen = b;
    }

    /**
     * Return true if slider menu is open
     * @return boolean
     */
    protected boolean getIsMenuOpen(){
        return this.isMenuOpen;
    }
}
