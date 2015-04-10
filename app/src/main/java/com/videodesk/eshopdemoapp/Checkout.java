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

    EditText co_fn, co_ln, co_email, co_ad1, co_ad2, co_ad3, co_city, co_zip, co_ccname,
            co_ccnumber, co_expm, co_expy;

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


        co_fn = (EditText) findViewById(R.id.checkout_firstname);
        co_ln = (EditText) findViewById(R.id.checkout_lastname);
        co_email = (EditText) findViewById(R.id.checkout_email);
        co_ad1 = (EditText) findViewById(R.id.checkout_address1);
        co_ad2 = (EditText) findViewById(R.id.checkout_address2);
        co_ad3 = (EditText) findViewById(R.id.checkout_address3);
        co_city = (EditText) findViewById(R.id.checkout_city);
        co_zip = (EditText) findViewById(R.id.checkout_zipcode);
        co_ccname = (EditText) findViewById(R.id.checkout_ccname);
        co_ccnumber = (EditText) findViewById(R.id.checkout_ccnumber);
        co_expm = (EditText) findViewById(R.id.checkout_expmonth);
        co_expy = (EditText) findViewById(R.id.checkout_expyear);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(co_fn, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_ln, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_email, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_ad1, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_ad2, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_ad3, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_city, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_zip, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_ccname, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_ccnumber, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_expm, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(co_expy, InputMethodManager.SHOW_IMPLICIT);
    }
}
