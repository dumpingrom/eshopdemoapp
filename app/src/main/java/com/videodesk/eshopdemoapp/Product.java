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
public class Product extends Activity {
    LinearLayout header_container = null;

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
            LinearLayout header = (LinearLayout)inflater.inflate(R.layout.header, product_container, true);
        } catch(InflateException e){
            Log.e("Inflater error********", ""+e);
        }

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

        Typeface roboto_bold = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Bold.ttf");
        Typeface georgia = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");


        switch (getCategory()){
            case "hats":
                header_container.removeAllViews();
                header_container.addView(header_hats);
                break;
            case "shoes":
                header_container.removeAllViews();
                header_container.addView(header_shoes);
                break;
            case "glasses":
                header_container.removeAllViews();
                header_container.addView(header_glasses);
                break;
            case "bags":
                header_container.removeAllViews();
                header_container.addView(header_bags);
                break;
            default:
                header_container.addView(findViewById(R.id.header_home));
        }

        header_hats_title.setTypeface(roboto_bold);
        header_shoes_title.setTypeface(roboto_bold);
        header_glasses_title.setTypeface(roboto_bold);
        header_bags_title.setTypeface(roboto_bold);

        num_hats.setTypeface(georgia);
        num_shoes.setTypeface(georgia);
        num_glasses.setTypeface(georgia);
        num_bags.setTypeface(georgia);

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

        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParams.width = pxFromDp(this, 220);
        imgParams.height = pxFromDp(this,120);
        imgParams.gravity = Gravity.CENTER;
        imgParams.setMargins(0,30,0,30);

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

                /*for (int i = 0; i<DataHolder.getInstance().getCart().size(); i++){
                    for (int j = 0; j < DataHolder.getInstance().getProduct(i).length; j++){
                        Log.d("PRODUCT ADDED*********", DataHolder.getInstance().getProduct(i)[j]);
                    }
                    Log.d("DEBUG********", "String[] length = "+DataHolder.getInstance().getProduct(i).length);
                }*/
                Toast.makeText(getApplicationContext(), getProductInfo(0) + " has been added to your cart!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Product.this, Cart.class);
                startActivity(intent);
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
    TOOLS
     */

    public static int dpFromPx(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().density);
    }

    public static int pxFromDp(final Context context, final float dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    /*
    END TOOLS
     */

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
}
