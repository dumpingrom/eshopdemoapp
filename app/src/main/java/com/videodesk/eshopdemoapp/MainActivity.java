package com.videodesk.eshopdemoapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends VdActivity {

    /*
    DECLARE VIEWS
     */
    LinearLayout home_container = null;

    LinearLayout slidermenu = null;
    ImageView slidermenu_hats = null;
    ImageView slidermenu_shoes = null;
    ImageView slidermenu_glasses = null;
    ImageView slidermenu_bags = null;
    ImageView slidermenu_cart = null;

    RelativeLayout header = null;
    TextView header_title;
    ImageView header_menu_icon;
    TextView header_home_title;

    RelativeLayout home_hats = null;

    ImageView home_hats_img = null;
    TextView home_hats_txt = null;
    TextView home_hats_num = null;

    RelativeLayout home_shoes = null;

    ImageView home_shoes_img = null;
    TextView home_shoes_txt = null;
    TextView home_shoes_num = null;

    RelativeLayout home_glasses = null;

    ImageView home_glasses_img = null;
    TextView home_glasses_txt = null;
    TextView home_glasses_num = null;

    RelativeLayout home_bags = null;

    ImageView home_bags_img = null;
    TextView home_bags_txt = null;
    TextView home_bags_num = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        SET VIEWS
         */


        slidermenu_hats = (ImageView)findViewById(R.id.slidemenu_hats);
        slidermenu_shoes = (ImageView)findViewById(R.id.slidemenu_shoes);
        slidermenu_glasses = (ImageView)findViewById(R.id.slidemenu_glasses);
        slidermenu_bags = (ImageView)findViewById(R.id.slidemenu_bags);
        slidermenu_cart = (ImageView)findViewById(R.id.slidemenu_cart);

        header = (RelativeLayout)findViewById(R.id.header);
        header_home_title = (TextView)findViewById(R.id.header_home_title);
        header_menu_icon = (ImageView)findViewById(R.id.header_menu_icon);


        home_hats = (RelativeLayout) findViewById(R.id.home_hats);
        home_hats_img = (ImageView) findViewById(R.id.home_hats_img);
        home_hats_txt = (TextView) findViewById(R.id.home_hats_txt);
        home_hats_num = (TextView) findViewById(R.id.home_hats_num);

        home_shoes = (RelativeLayout) findViewById(R.id.home_shoes);
        home_shoes_img = (ImageView) findViewById(R.id.home_shoes_img);
        home_shoes_txt = (TextView) findViewById(R.id.home_shoes_txt);
        home_shoes_num = (TextView) findViewById(R.id.home_shoes_num);

        home_glasses = (RelativeLayout) findViewById(R.id.home_glasses);
        home_glasses_img = (ImageView) findViewById(R.id.home_glasses_img);
        home_glasses_txt = (TextView) findViewById(R.id.home_glasses_txt);
        home_glasses_num = (TextView) findViewById(R.id.home_glasses_num);

        home_bags = (RelativeLayout) findViewById(R.id.home_bags);
        home_bags_img = (ImageView) findViewById(R.id.home_bags_img);
        home_bags_txt = (TextView) findViewById(R.id.home_bags_txt);
        home_bags_num = (TextView) findViewById(R.id.home_bags_num);

        /*
        SET FONTS
         */
        header_home_title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf"));

        home_hats_txt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf"));
        home_shoes_txt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf"));
        home_glasses_txt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf"));
        home_bags_txt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf"));

        home_hats_num.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
        home_shoes_num.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
        home_glasses_num.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
        home_bags_num.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));


        /*
        SET HANDLERS
         */
        home_hats.setOnClickListener(handler);
        home_shoes.setOnClickListener(handler);
        home_glasses.setOnClickListener(handler);
        home_bags.setOnClickListener(handler);
        slidermenu_hats.setOnClickListener(handler);
        slidermenu_shoes.setOnClickListener(handler);
        slidermenu_glasses.setOnClickListener(handler);
        slidermenu_bags.setOnClickListener(handler);
        slidermenu_cart.setOnClickListener(handler);
        header_menu_icon.setOnClickListener(handler);
    }

    /*
    HANDLER
     */
    View.OnClickListener handler = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if (v == home_hats || v == slidermenu_hats){
                Intent i = new Intent(MainActivity.this , Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "hats");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                //finish();
            }
            else if (v == home_shoes || v == slidermenu_shoes){
                Intent i = new Intent(MainActivity.this , Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "shoes");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                //finish();
            }
            else if (v == home_glasses || v == slidermenu_glasses){
                Intent i = new Intent(MainActivity.this, Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "glasses");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                //finish();
            }
            else if(v == home_bags || v == slidermenu_bags){
                Intent i = new Intent(MainActivity.this, Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "bags");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                //finish();
            }
            else if(v == slidermenu_cart){
                Intent i = new Intent(MainActivity.this, Cart.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
            else if(v == header_menu_icon){
                launchMenuAnimation(slidermenu);
            }
        }
    };
}
