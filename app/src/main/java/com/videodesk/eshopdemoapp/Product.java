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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romain@videodesk on 03/03/15.
 */
public class Product extends VdActivity {
    LinearLayout slidermenu = null;
    ImageView slidermenu_hats = null;
    ImageView slidermenu_shoes = null;
    ImageView slidermenu_glasses = null;
    ImageView slidermenu_bags = null;
    ImageView slidermenu_cart = null;

    boolean isMenuOpen;

    LinearLayout header_container = null;
    LinearLayout header;
    TextView header_title;
    TextView header_num;
    ImageView header_menu_icon;

    String[] productInfo = new String[5];
    String category = "";



    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setProductInfo(b.getStringArray("productInfo"));
        setCategory(b.getString("cat"));

        setContentView(R.layout.product);

        LinearLayout product_container = (LinearLayout)findViewById(R.id.product_container);

        LayoutInflater inflater;
        inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        try{
            header = (LinearLayout)inflater.inflate(R.layout.header, product_container, true);
            slidermenu = (LinearLayout)inflater.inflate(R.layout.slider_menu, null);
            product_container.addView(slidermenu, 0);
        } catch(InflateException e){
            Log.e("Inflater error********", ""+e);
        }

        /*
        SET VIEWS
         */

        header_container = (LinearLayout)findViewById(R.id.header_container);
        header = (LinearLayout)findViewById(R.id.header);
        header_title = (TextView)findViewById(R.id.header_title);
        header_num = (TextView)findViewById(R.id.header_num);
        header_menu_icon = (ImageView)findViewById(R.id.header_menu_icon);

        /*
        END SET VIEWS
         */

        /*
        SLIDER MENU
         */

        slidermenu_hats = (ImageView)findViewById(R.id.slidemenu_hats);
        slidermenu_shoes = (ImageView)findViewById(R.id.slidemenu_shoes);
        slidermenu_glasses = (ImageView)findViewById(R.id.slidemenu_glasses);
        slidermenu_bags = (ImageView)findViewById(R.id.slidemenu_bags);
        slidermenu_cart = (ImageView)findViewById(R.id.slidemenu_cart);
        slidermenu_hats.setOnClickListener(handler);
        slidermenu_shoes.setOnClickListener(handler);
        slidermenu_glasses.setOnClickListener(handler);
        slidermenu_bags.setOnClickListener(handler);
        slidermenu_cart.setOnClickListener(handler);
        header_menu_icon.setOnClickListener(handler);


        setIsMenuOpen(false);

        /*
        END SLIDER MENU
         */

        Typeface roboto_bold = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Bold.ttf");
        Typeface georgia = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");

        /*
        TODO
        Simplify header generation (set text + bg-col + num...)
         */
        int colorId;
        CharSequence numId;
        switch (getCategory()){
            case "hats":
                colorId = getResources().getColor(R.color.color_hats);
                numId = getResources().getText(R.string.one);
                break;
            case "shoes":
                colorId = getResources().getColor(R.color.color_shoes);
                numId = getResources().getText(R.string.two);
                break;
            case "glasses":
                colorId = getResources().getColor(R.color.color_glasses);
                numId = getResources().getText(R.string.three);
                break;
            case "bags":
                colorId = getResources().getColor(R.color.color_bags);
                numId = getResources().getText(R.string.four);
                break;
            default:
                colorId = getResources().getColor(R.color.white);
                numId = "";
                break;
        }

        header.setBackgroundColor(colorId);
        header_num.setText(numId);
        slidermenu.setBackgroundColor(colorId);
        header_title.setTypeface(roboto_bold);
        header_num.setTypeface(georgia);

        product_container.setGravity(Gravity.CENTER_HORIZONTAL);
        product_container.addView(addProductView());

    }

    public ScrollView addProductView(){
        ScrollView scrollView = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        /*CREATE IMAGE HEADER*/
        ImageView img = new ImageView(this);
        int drawableId = this.getResources().getIdentifier(getProductInfo(4), "drawable", this.getPackageName());
        img.setImageResource(drawableId);
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        img.setId(View.generateViewId());
        img.setPadding(dpFromPx(this, 50),dpFromPx(this, 50),dpFromPx(this, 50),dpFromPx(this, 50));
        //img.setMaxHeight();

        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, pxFromDp(this, 350));
        //imgParams.width = pxFromDp(this, 220);
        //imgParams.height = pxFromDp(this,120);
        imgParams.weight = (float)0.70;
        imgParams.gravity = Gravity.CENTER;

        img.setLayoutParams(imgParams);


        /*CREATE CONTENT RELATIVE LAYOUT*/
        RelativeLayout content = new RelativeLayout(this);
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.setMargins(pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20));
        content.setPadding(10,10,10,10);
        content.setBackgroundColor(Color.WHITE);
        content.setLayoutParams(rlp);

            /*
            CREATE CONTENT
             */
        //title
        TextView title = new TextView(this);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        title.setTextColor(Color.BLACK);
        title.setText(getProductInfo(0));
        title.setId(View.generateViewId());

        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(pxFromDp(this, 10), pxFromDp(this, 10),0,0);

        title.setLayoutParams(titleParams);

        //short description
        TextView shortDesc = new TextView(this);
        shortDesc.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Regular.ttf"));
        shortDesc.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        shortDesc.setTextColor(Color.BLACK);
        shortDesc.setText(getProductInfo(1));
        shortDesc.setId(View.generateViewId());

        RelativeLayout.LayoutParams shortDescParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        shortDescParams.setMargins(pxFromDp(this, 12),  0, 0, pxFromDp(this, 30));
        shortDescParams.addRule(RelativeLayout.BELOW, title.getId());

        shortDesc.setLayoutParams(shortDescParams);

        //long description
        TextView longDesc = new TextView(this);
        longDesc.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Regular.ttf"));
        longDesc.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        longDesc.setTextColor(Color.BLACK);
        longDesc.setText(getProductInfo(2));
        longDesc.setId(View.generateViewId());
        //Log.d("LONG DESC CONTENT******",longDesc.getText().toString());

        RelativeLayout.LayoutParams longDescParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        longDescParams.setMargins(pxFromDp(this, 10), 0, 0, pxFromDp(this, 20));
        longDescParams.addRule(RelativeLayout.BELOW, shortDesc.getId());

        longDesc.setLayoutParams(longDescParams);

        //price
        TextView price = new TextView(this);
        price.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
        price.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        price.setTextColor(Color.BLACK);
        price.setText("Price: $"+getProductInfo(3));
        price.setId(View.generateViewId());


        //ADD TO CART button
        Button addToCart = new Button(this);
        addToCart.setBackgroundColor(Color.BLACK);
        addToCart.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        addToCart.setTextColor(Color.WHITE);
        addToCart.setText("ADD TO CART");
        addToCart.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf"));
        addToCart.setPadding(pxFromDp(this, 15), pxFromDp(this, 10), pxFromDp(this, 15), 0);
        addToCart.setId(View.generateViewId());

        RelativeLayout.LayoutParams addToCartParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addToCartParams.addRule(RelativeLayout.BELOW, longDesc.getId());
        addToCartParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        addToCartParams.setMargins(0, pxFromDp(this,10), 0, 0);

        addToCart.setLayoutParams(addToCartParams);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.getInstance().setCart(getProduct());
                //ADD INTENT HERE

                Toast.makeText(getApplicationContext(), getProductInfo(0) + " has been added to your cart!", Toast.LENGTH_LONG).show();

                /*Intent intent = new Intent(Product.this, Cart.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);*/
                finish();
            }
        });


        //price rules need to be declared here because depending on addToCart rules
        RelativeLayout.LayoutParams priceParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        priceParams.setMargins(pxFromDp(this, 10), pxFromDp(this, 10), 0, 0);
        priceParams.addRule(RelativeLayout.BELOW, longDesc.getId());
        priceParams.addRule(RelativeLayout.TEXT_ALIGNMENT_GRAVITY, RelativeLayout.CENTER_VERTICAL);

        price.setLayoutParams(priceParams);

            /*
            END CREATE CONTENT
             */

        /*
        ADD CONTENT TO RELATIVE LAYOUT
         */
        content.addView(title);
        content.addView(shortDesc);
        content.addView(longDesc);
        content.addView(price);
        content.addView(addToCart);

        /*
        ADD IMAGE HEADER AND RELATIVE LAYOUT TO LINEAR LAYOUT
         */
        ll.setLayoutParams(llp);
        ll.setBackgroundColor(Color.parseColor("#E7E7E7"));
        ll.addView(img);
        ll.addView(content);

        scrollView.addView(ll);
        return scrollView;
    }

    /*
    GETTERS / SETTERS
     */
    public void setProductInfo(String[] pi){
        productInfo = pi;
    }

    public String getProductInfo( int i){
        return productInfo[i];
    }

    public String[] getProduct(){ return productInfo;}

    public void setCategory(String cat){
        category = cat;
    }

    public String getCategory(){
        return category;
    }

    /*
    END GETTERS / SETTERS
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
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
            else if (v == slidermenu_shoes){
                Intent i = new Intent(getApplicationContext() , Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "shoes");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
            else if (v == slidermenu_glasses){
                Intent i = new Intent(getApplicationContext(), Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "glasses");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
            else if(v == slidermenu_bags){
                Intent i = new Intent(getApplicationContext(), Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "bags");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
            else if(v == slidermenu_cart){
                Intent i = new Intent(getApplicationContext(), Cart.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
            else if(v == header_menu_icon){
                launchMenuAnimation(slidermenu);
            }

            //must not finish activity on header click
            if (v.getParent() == slidermenu){
                finish();
            }
        }
    };
    /*
    END HANDLER
     */
}
