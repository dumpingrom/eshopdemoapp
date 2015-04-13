package com.videodesk.eshopdemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by videodesk on 09/04/15.
 *
 * This is the mother class of all activities, it contains useful tools and attrs
 */
public class VdActivity extends Activity {
    boolean isMenuOpen = false;
    protected Typeface georgia;
    protected Typeface roboto;
    protected Typeface roboto_bold;
    protected Typeface roboto_black;

    protected View.OnClickListener handler;

    protected void init() {
        this.georgia = Typeface.createFromAsset(getResources().getAssets(), "fonts/Georgia.ttf");
        this.roboto = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto_Regular.ttf");
        this.roboto_bold = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto_Bold.ttf");
        this.roboto_black = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto_Black.ttf");

        this.handler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.toString().contains("hats")){
                    Intent i = new Intent(VdActivity.this , Category.class);
                    Bundle b = new Bundle();
                    b.putString("cat", "hats");
                    i.putExtras(b);
                    startActivity(i);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    //finish();
                }
                else if (v.toString().contains("shoes")){
                    Intent i = new Intent(VdActivity.this , Category.class);
                    Bundle b = new Bundle();
                    b.putString("cat", "shoes");
                    i.putExtras(b);
                    startActivity(i);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    //finish();
                }
                else if (v.toString().contains("glasses")){
                    Intent i = new Intent(VdActivity.this, Category.class);
                    Bundle b = new Bundle();
                    b.putString("cat", "glasses");
                    i.putExtras(b);
                    startActivity(i);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    //finish();
                }
                else if(v.toString().contains("bags")){
                    Intent i = new Intent(VdActivity.this, Category.class);
                    Bundle b = new Bundle();
                    b.putString("cat", "bags");
                    i.putExtras(b);
                    startActivity(i);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    //finish();
                }
                else if(v.toString().contains("cart")){
                    Intent i = new Intent(VdActivity.this, Cart.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
                else if(v.toString().contains("menu_icon")){
                    launchMenuAnimation(findViewById(R.id.slidermenu));
                }
            }
        };
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

        a.setDuration(250);
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

        a.setDuration(250);
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

    /**
     * Recursive function allowing to get all children from a View tree
     *
     * @param v The container of which children are being returned
     * @return An ArrayList with all children of v
     */
    protected ArrayList<View> getAllChildren(View v) {
        if(!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            ArrayList<View> viewArrayList = new ArrayList<>();
            if(!(viewArrayList.contains(v))) {
                viewArrayList.add(v);
            }
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }
}
