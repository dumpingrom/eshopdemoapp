package com.videodesk.eshopdemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Created by romain@videodesk on 23/02/15.
 */

public class Category extends Activity {
    LinearLayout header_container = null;
    LinearLayout header;

    LinearLayout slidermenu = null;
    ImageView slidermenu_hats = null;
    ImageView slidermenu_shoes = null;
    ImageView slidermenu_glasses = null;
    ImageView slidermenu_bags = null;
    ImageView slidermenu_cart = null;

    boolean isMenuOpen;

    ScrollView scr = null;
    InputStream stream = null;

    LinearLayout header_hats = null;
    LinearLayout header_shoes = null;
    LinearLayout header_glasses = null;
    LinearLayout header_bags = null;

    TextView header_hats_title = null;
    TextView header_shoes_title = null;
    TextView header_glasses_title = null;
    TextView header_bags_title = null;

    TextView num_hats = null;
    TextView num_shoes = null;
    TextView num_glasses = null;
    TextView num_bags = null;

    public String category = "";
    public String[][] productInfo = new String[10][5];

    int rlId;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setCategory(b.getString("cat"));

        setContentView(R.layout.category);

        LinearLayout category_container = (LinearLayout)findViewById(R.id.category_container);

        LayoutInflater inflater;
        inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        try{
            header = (LinearLayout)inflater.inflate(R.layout.header, category_container, true);
            slidermenu = (LinearLayout)inflater.inflate(R.layout.slider_menu, null);
            category_container.addView(slidermenu, 0);
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

        header_container = (LinearLayout)findViewById(R.id.header_container);

        header_hats = (LinearLayout)findViewById(R.id.header_hats);
        header_shoes = (LinearLayout)findViewById(R.id.header_shoes);
        header_glasses = (LinearLayout)findViewById(R.id.header_glasses);
        header_bags = (LinearLayout)findViewById(R.id.header_bags);

        header_hats_title = (TextView)findViewById(R.id.header_hats_title);
        header_shoes_title = (TextView)findViewById(R.id.header_shoes_title);
        header_glasses_title = (TextView)findViewById(R.id.header_glasses_title);
        header_bags_title = (TextView)findViewById(R.id.header_bags_title);

        num_hats = (TextView)findViewById(R.id.header_hats_num);
        num_shoes = (TextView)findViewById(R.id.header_shoes_num);
        num_glasses = (TextView)findViewById(R.id.header_glasses_num);
        num_bags = (TextView)findViewById(R.id.header_bags_num);

        /*
        END SET VIEWS
         */

        Typeface roboto_bold = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Bold.ttf");
        Typeface georgia = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");


        switch (getCategory()){
            case "hats":
                header_container.removeAllViews();
                header_container.addView(header_hats);
                slidermenu.setBackgroundColor(getResources().getColor(R.color.color_hats));
                break;
            case "shoes":
                header_container.removeAllViews();
                header_container.addView(header_shoes);
                slidermenu.setBackgroundColor(getResources().getColor(R.color.color_shoes));
                break;
            case "glasses":
                header_container.removeAllViews();
                header_container.addView(header_glasses);
                slidermenu.setBackgroundColor(getResources().getColor(R.color.color_glasses));
                break;
            case "bags":
                header_container.removeAllViews();
                header_container.addView(header_bags);
                slidermenu.setBackgroundColor(getResources().getColor(R.color.color_bags));
                break;
            default:
                header_container.removeAllViews();
                header_container.addView(findViewById(R.id.header_home));
                slidermenu.setBackgroundColor(getResources().getColor(R.color.white));
                break;
        }
        //header_container.addView(slider_menu, 0);

        header_hats_title.setTypeface(roboto_bold);
        header_shoes_title.setTypeface(roboto_bold);
        header_glasses_title.setTypeface(roboto_bold);
        header_bags_title.setTypeface(roboto_bold);

        num_hats.setTypeface(georgia);
        num_shoes.setTypeface(georgia);
        num_glasses.setTypeface(georgia);
        num_bags.setTypeface(georgia);

         /*
        SET HANDLER
         */
        slidermenu_hats.setOnClickListener(handler);
        slidermenu_shoes.setOnClickListener(handler);
        slidermenu_glasses.setOnClickListener(handler);
        slidermenu_bags.setOnClickListener(handler);
        slidermenu_cart.setOnClickListener(handler);
        header_hats.setOnClickListener(handler);
        header_shoes.setOnClickListener(handler);
        header_glasses.setOnClickListener(handler);
        header_bags.setOnClickListener(handler);
        /*
        END SET HANDLER
         */

        /*
        OPEN XML STREAM AND PARSE CONTENT (cat from Bundle)
         */
        try{
            switch(getCategory()){
                case "hats":
                    stream = getApplicationContext().getAssets().open("xml/hats.xml");
                    break;
                case "shoes":
                    stream = getApplicationContext().getAssets().open("xml/hats.xml");
                    break;
                case "glasses":
                    stream = getApplicationContext().getAssets().open("xml/glasses.xml");
                    break;
                case "bags":
                    stream = getApplicationContext().getAssets().open("xml/bags.xml");
                    break;
            }
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
            parser.setInput(stream, null);

           header_container.addView(parseXml(parser));
            //scr.addView(header_container);
        } catch ( IOException|XmlPullParserException e ) {
            e.printStackTrace();
        }
    }

    private ScrollView parseXml(XmlPullParser xpp) throws XmlPullParserException, IOException{
        int e = xpp.getEventType();
        String name = null;
        int previousEvent = 0;
        String previousText = null;

        ScrollView scrollView = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setWeightSum(10);
        ll.setBackgroundColor(Color.parseColor("#E7E7E7"));
        ll.setGravity(Gravity.CENTER);

        RelativeLayout rl = null;
        int rlId = 1;
        TextView title = null;
        TextView description = null;
        TextView price = null;
        ImageView img = null;


        while (e != XmlPullParser.END_DOCUMENT){
            name = xpp.getName();
            switch(e){
                case XmlPullParser.START_DOCUMENT:
                   //Log.d("log", "Document Started");
                    break;

                case XmlPullParser.START_TAG:
                    //Log.d("log", "<"+name+">");

                    if("product".equals(xpp.getName())){
                        rl = new RelativeLayout(this);

                        //set Relative Layout rules
                        //lp.addRule(RelativeLayout.END_OF, rlId);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(pxFromDp(this, 20), pxFromDp(this, 10), pxFromDp(this, 20), pxFromDp(this, 10));

                        rl.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        rl.setLayoutParams(lp);
                        rl.setPadding(10,10,10,10);

                        rl.setId(rlId);
                        Log.d("SETID*************", "Just created Relative Layout with id"+rlId);
                        rlId++;
                    }
                    previousText = xpp.getName();
                    break;

                case XmlPullParser.TEXT:
                    if(previousEvent != XmlPullParser.END_TAG && !"".equals(xpp.getText())){
                        switch(previousText){
                            case "name":
                                //Log.d("log", "Product name: "+xpp.getText());
                                setStringBundle(rl.getId(), 0, xpp.getText());
                                Log.d("Bundle", "Set productInfo["+rl.getId()+"][0] to: "+getString(rl.getId(), 0));

                                title = new TextView(this);
                                title.setText(xpp.getText());
                                title.setTextColor(Color.BLACK);
                                title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
                                title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                                RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                titleParams.setMargins(pxFromDp(this, 150), pxFromDp(this, 30), 0, 0);

                                title.setLayoutParams(titleParams);

                                if(rl != null){
                                    rl.addView(title);
                                }
                                break;
                            case "description":
                                //Log.d("log", "Product description: "+xpp.getText());
                                setStringBundle(rl.getId(), 1, xpp.getText());
                                Log.d("Bundle", "Set productInfo["+rl.getId()+"][1] to: "+getString(rl.getId(), 1));

                                description = new TextView(this);

                                description.setText(xpp.getText());
                                description.setTextColor(Color.BLACK);
                                description.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Regular.ttf"));
                                description.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
                                description.setId(View.generateViewId());

                                RelativeLayout.LayoutParams descParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                descParams.setMargins(pxFromDp(this, 150), pxFromDp(this, 80), 0, 0);

                                description.setLayoutParams(descParams);

                                if(rl != null){
                                    rl.addView(description);
                                }
                                break;
                            case "long":
                                setStringBundle(rl.getId(), 2, xpp.getText());
                                Log.d("Bundle", "Set productInfo["+rl.getId()+"][2] to: "+getString(rl.getId(), 2));
                                break;
                            case "price":
                                //Log.d("log", "Product price: "+xpp.getText());
                                setStringBundle(rl.getId(), 3, xpp.getText());
                                Log.d("Bundle", "Set productInfo["+rl.getId()+"][3] to: "+getString(rl.getId(), 3));

                                price = new TextView(this);
                                price.setText("$ "+xpp.getText());
                                price.setTextColor(Color.BLACK);
                                price.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
                                price.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                                RelativeLayout.LayoutParams priceParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                                if(description != null){
                                    priceParams.setMargins(pxFromDp(this, 150), pxFromDp(this, 20), 0, 0);
                                    priceParams.addRule(RelativeLayout.BELOW, description.getId());
                                }


                                price.setLayoutParams(priceParams);

                                if(rl != null){
                                    rl.addView(price);
                                }
                                break;
                            case "image":
                                //Log.d("log", "Product image URL: "+xpp.getText());
                                setStringBundle(rl.getId(), 4, xpp.getText());
                                Log.d("Bundle", "Set productInfo["+rl.getId()+"][4] to: "+getString(rl.getId(), 4));

                                String imgUrl = xpp.getText();

                                img = new ImageView(this);
                                int drawableId = this.getResources().getIdentifier(imgUrl, "drawable", this.getPackageName());
                                img.setImageResource(drawableId);


                                RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                imgParams.width = pxFromDp(this, 100);
                                imgParams.height = pxFromDp(this, 100);
                                imgParams.setMargins(pxFromDp(this, 30),0,0,0);
                                imgParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
                                imgParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

                                img.setLayoutParams(imgParams);

                                //Log.d("Info", "DrawableId = " + drawableId);
                                if(rl != null){
                                    rl.addView(img);
                                }
                                break;
                            default:
                                //Log.d("log", "Text not found");
                                break;
                        }
                        if(rl != null) {
                            rl.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = 0;
                                    for (int i = 0; i < productInfo[0].length; i++){
                                        if (i == v.getId()){
                                            id = i;
                                        }
                                    }
                                    Log.d("RL ID******************", "id =" + id);
                                    Intent i = new Intent(Category.this, Product.class);
                                    Bundle b = new Bundle();
                                    b.putStringArray("productInfo", getStringBundle(id));//must not use 0 as an id
                                    b.putString("cat", getCategory());
                                    i.putExtras(b);
                                    startActivity(new Intent(getBaseContext(), Product.class).putExtras(b));
                                    //finish();
                                }
                            });
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    //Log.d("log", "</"+xpp.getName()+">");

                    if("product".equals(xpp.getName())){
                        ll.addView(rl);
                }
                    break;
            }
            previousEvent = xpp.getEventType();

            e = xpp.next();
        }
        scrollView.addView(ll);
        return scrollView;
    }

    /*
    TOOLS
     */

    public static int dpFromPx(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().density);
    }

    public static int pxFromDp(final Context context, final float dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    public void expand(final View v){
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = pxFromDp(this.getApplicationContext(), 75);

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);

        Animation a = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1 ? LinearLayout.LayoutParams.WRAP_CONTENT : (int)(targetHeight*interpolatedTime);
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

    public void collapse(final View v){
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }
                else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
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

    /*
    END TOOLS
     */

    /***************************
     * GETTERS / SETTERS
     */

    public void setCategory(String str){
        category = str;
    }

    public String getCategory(){
        return category;
    }

    public void setStringBundle(int id, int index, String str){
        productInfo[id][index] = str;
    }

    public String getString(int id, int index){
        return productInfo[id][index];
    }

    public String[] getStringBundle(int id){
        return productInfo[id];
    }

    private void setIsMenuOpen(boolean b){
        this.isMenuOpen = b;
    }

    private boolean getIsMenuOpen(){
        return this.isMenuOpen;
    }
    /*****************
     * END GETTERS / SETTERS
     */

    /*
    HANDLER
     */
    View.OnClickListener handler = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if (v == slidermenu_hats){
                Intent i = new Intent(getApplicationContext(), Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "hats");
                i.putExtras(b);
                startActivity(i);
                //finish();
            }
            else if (v == slidermenu_shoes){
                Intent i = new Intent(getApplicationContext() , Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "shoes");
                i.putExtras(b);
                startActivity(i);
                //finish();
            }
            else if (v == slidermenu_glasses){
                Intent i = new Intent(getApplicationContext(), Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "glasses");
                i.putExtras(b);
                startActivity(i);
                //finish();
            }
            else if(v == slidermenu_bags){
                Intent i = new Intent(getApplicationContext(), Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "bags");
                i.putExtras(b);
                startActivity(i);
                //finish();
            }
            else if(v == slidermenu_cart){
                Intent i = new Intent(getApplicationContext(), Cart.class);
                startActivity(i);
            }
            else if(v == header_hats || v == header_shoes || v == header_glasses || v == header_bags){
                if(getIsMenuOpen() == true){
                    Log.d("isMenuOpen = ", ""+getIsMenuOpen());
                    collapse(slidermenu);
                    setIsMenuOpen(false);
                }
                else{
                    Log.d("isMenuOpen = ", ""+getIsMenuOpen());
                    expand(slidermenu);
                    setIsMenuOpen(true);
                }
            }
        }
    };
    /*
    END HANDLER
     */
}
