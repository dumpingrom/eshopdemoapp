package com.videodesk.eshopdemoapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Created by videodesk on 09/04/15.
 */
public class VdActivity extends Activity {
    boolean isMenuOpen = false;
    LinearLayout slidermenu;

    public void initSlider() {
        try{
            LayoutInflater inflater;

            inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            this.slidermenu = (LinearLayout)inflater.inflate(R.layout.slider_menu, null);
        } catch(InflateException e){
            Log.e("Inflater error********", ""+e);
        }
    }

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
     *
     * @param v
     *
     * Expand the menu area by 75 dp.
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

    public int dpFromPx(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().density);
    }

    public int pxFromDp(final Context context, final float dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    protected void setIsMenuOpen(boolean b){
        this.isMenuOpen = b;
    }

    protected boolean getIsMenuOpen(){
        return this.isMenuOpen;
    }
}
