package com.videodesk.eshopdemoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends VdActivity {

    /*
    DECLARE VIEWS
     */
    LinearLayout home_container = null;
    LinearLayout slidermenu = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main);

        home_container = (LinearLayout)findViewById(R.id.home_container);

        /*
        PREPEND SLIDER MENU
         */
        LayoutInflater inflater;
        inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        try{
            slidermenu = (LinearLayout)inflater.inflate(R.layout.slider_menu, null);
            home_container.addView(slidermenu, 0);
        } catch(InflateException e){
            Log.e("Inflater error********", ""+e);
        }

        /*
        SET FONTS AND HANDLERS
         */

        ArrayList<View> allViews = getAllChildren(home_container);

        for (View child : allViews) {
            Log.d("ALL VIEWS =>", child.toString());
            if(child instanceof TextView) {
                if(child.toString().contains("_txt") || child.toString().contains("_title")) {
                    ((TextView) child).setTypeface(roboto_black);
                    Log.d("FONTS******", "Setting roboto_black to \n" + child.toString());
                }
                if(child.toString().contains("_num")) {
                    ((TextView) child).setTypeface(georgia);
                    Log.d("FONTS******", "Setting georgia to \n" + child.toString());
                }
            }
            if(child.isClickable()) {
                child.setOnClickListener(handler);
                Log.d("HANDLER******", "Setting handler on \n" + child.toString());
            }
        }
    }
}
