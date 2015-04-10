package com.videodesk.eshopdemoapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.videodesk.eshopdemoapp.R.layout.checkout;

/**
 * Created by romain@videodesk on 13/02/15.
 */
public class Checkout extends VdActivity {
    LinearLayout checkout_container;

    Button submit;

    LinearLayout header;
    TextView header_home_title;
    TextView header_title;
    ImageView header_menu_icon;

    LinearLayout slidermenu;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(checkout);
        //Set Views
        checkout_container = (LinearLayout)findViewById(R.id.checkout_container);
        header = (LinearLayout)findViewById(R.id.header);
        header_menu_icon = (ImageView)findViewById(R.id.header_menu_icon);
        header_home_title = (TextView)findViewById(R.id.header_home_title);
        header_title = (TextView)findViewById(R.id.header_title);
        header_home_title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf"));
        header_title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Bold.ttf"));
        submit = (Button)findViewById(R.id.checkout_submit);

        submit.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Bold.ttf"));

        LayoutInflater inflater;
        inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        try{
            slidermenu = (LinearLayout)inflater.inflate(R.layout.slider_menu, null);
            checkout_container.addView(slidermenu, 0);
        } catch(InflateException e){
            Log.e("Inflater error********", "" + e);
        }

        header_menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == header_menu_icon) {
                    launchMenuAnimation(slidermenu);
                }
            }
        });
    }
}
