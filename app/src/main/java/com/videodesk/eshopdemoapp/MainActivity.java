package com.videodesk.eshopdemoapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    /*
    DECLARE VIEWS
     */

    /*TextView label_hats = null;
    ImageView icon_hats = null;

    TextView label_eyewear = null;
    ImageView icon_eyewear = null;

    TextView label_gloves = null;
    ImageView icon_gloves = null;

    TextView label_bags = null;
    ImageView icon_bags = null;*/

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

    /*
    END DECLARE VIEWS
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Typeface georgia = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Regular.ttf");
        Typeface roboto_black = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf");
        Typeface roboto_bold = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Bold.ttf");
        Typeface roboto_italic = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Italic.ttf");

        setContentView(R.layout.activity_main);

        /*
        FIND VIEWS
         */

        LinearLayout home_container = (LinearLayout)findViewById(R.id.home_container);

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
        END FIND VIEWS
         */

        /*
        SET FONTS
         */
        home_hats_txt.setTypeface(roboto_black);
        home_shoes_txt.setTypeface(roboto_black);
        home_glasses_txt.setTypeface(roboto_black);
        home_bags_txt.setTypeface(roboto_black);

        home_hats_num.setTypeface(georgia);
        home_shoes_num.setTypeface(georgia);
        home_glasses_num.setTypeface(georgia);
        home_bags_num.setTypeface(georgia);

        /*
        END SET FONTS
         */



        /*
        SET HANDLERS
         */

        home_hats.setOnClickListener(handler);
        home_shoes.setOnClickListener(handler);
        home_glasses.setOnClickListener(handler);
        home_bags.setOnClickListener(handler);


        /*
        END SET HANDLERS
         */
    }

    /*
    HANDLER
     */
    View.OnClickListener handler = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if (v == home_hats){
                Intent i = new Intent(MainActivity.this , Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "hats");
                i.putExtras(b);
                startActivity(i);
                finish();
            }
            else if (v == home_shoes){
                Intent i = new Intent(MainActivity.this , Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "shoes");
                i.putExtras(b);
                startActivity(i);
                finish();
            }
            else if (v == home_glasses){
                Intent i = new Intent(MainActivity.this, Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "glasses");
                i.putExtras(b);
                startActivity(i);
                finish();
            }
            else if(v == home_bags){
                Intent i = new Intent(MainActivity.this, Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "bags");
                i.putExtras(b);
                startActivity(i);
                finish();
            }
        }
    };
    /*
    END HANDLER
     */

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}
