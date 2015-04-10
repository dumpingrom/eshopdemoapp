package com.videodesk.eshopdemoapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by romain@videodesk on 04/03/15.
 */
public class Cart extends VdActivity {
    LinearLayout cart;

    LinearLayout cartContainer;

    RelativeLayout header;
    TextView header_title;
    TextView header_home_title;
    ImageView header_menu_icon;

    Button tmp_checkout_btn = null;
    LinearLayout slidermenu;
    ImageView slidermenu_hats;
    ImageView slidermenu_shoes;
    ImageView slidermenu_glasses;
    ImageView slidermenu_bags;

    ImageView slidermenu_cart;

    int totalPrice = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        setIsMenuOpen(false);

        /**
         * VIEWS
         */
        cart = (LinearLayout)findViewById(R.id.cart);
        cartContainer = (LinearLayout) findViewById(R.id.cart_container);
        header_home_title = (TextView)findViewById(R.id.header_home_title);
        header_title = (TextView)findViewById(R.id.header_cart_title);

        LayoutInflater inflater;
        inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        try{
            slidermenu = (LinearLayout)inflater.inflate(R.layout.slider_menu, null);
            cart.addView(slidermenu, 0);
        } catch(InflateException e){
            Log.e("Inflater error********", ""+e);
        }
        slidermenu.setBackgroundColor(getResources().getColor(R.color.color_hats));
        header = (RelativeLayout)findViewById(R.id.header);
        header_menu_icon = (ImageView)findViewById(R.id.header_menu_icon);

        slidermenu_hats = (ImageView)findViewById(R.id.slidemenu_hats);
        slidermenu_shoes = (ImageView)findViewById(R.id.slidemenu_shoes);
        slidermenu_glasses = (ImageView)findViewById(R.id.slidemenu_glasses);
        slidermenu_bags = (ImageView)findViewById(R.id.slidemenu_bags);
        slidermenu_cart = (ImageView)findViewById(R.id.slidemenu_cart);

        /**
         * SLIDER MENU
         */

        slidermenu_hats.setOnClickListener(handler);
        slidermenu_shoes.setOnClickListener(handler);
        slidermenu_glasses.setOnClickListener(handler);
        slidermenu_bags.setOnClickListener(handler);
        slidermenu_cart.setOnClickListener(handler);
        header_menu_icon.setOnClickListener(handler);

        header_title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Bold.ttf"));
        header_home_title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf"));


        if(DataHolder.getInstance().getCart().size() == 0){
            //CREATE RELATIVE LAYOUT CONTAINER
            RelativeLayout emptyCart = new RelativeLayout(this);
            LinearLayout.LayoutParams emptyCartParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            emptyCartParams.setMargins(pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20));
            emptyCart.setLayoutParams(emptyCartParams);
            emptyCart.setPadding(pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20));
            emptyCart.setBackgroundColor(Color.WHITE);

            //CREATE TEXT VIEW FOR EMPTY CART
            TextView emptyCartTxt = new TextView(this);
            emptyCartTxt.setText(getResources().getText(R.string.cart_empty));
            RelativeLayout.LayoutParams emptyCartTxtParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            emptyCartTxtParams.setMargins(0, 0, 0, 0);
            emptyCartTxtParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            emptyCartTxtParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            emptyCartTxt.setLayoutParams(emptyCartTxtParams);
            emptyCartTxt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Regular.ttf"));
            emptyCartTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            emptyCartTxt.setTextColor(Color.BLACK);
            emptyCartTxt.setId(View.generateViewId());

            //ADD TEXT VIEW TO RELATIVE LAYOUT
            emptyCart.addView(emptyCartTxt);

            //ADD RELATIVE LAYOUT TO MAIN VIEW (cartContainer)
            cartContainer.addView(emptyCart);

        }
        else{
            for (int i = 0; i<DataHolder.getInstance().getCart().size(); i++){
                //Log.d("DEBUG********", "String[] length = "+DataHolder.getInstance().getProduct(i).length);
                cartContainer.addView(cartItem(i));
            }

            /*
            TOTAL PRICE VIEW
             */
            //CREATE RELATIVE LAYOUT CONTAINER
            RelativeLayout totalPriceLayout = new RelativeLayout(this);
            LinearLayout.LayoutParams totalPriceLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            totalPriceLayoutParams.setMargins(pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20));
            totalPriceLayout.setLayoutParams(totalPriceLayoutParams);
            totalPriceLayout.setPadding(pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20));
            totalPriceLayout.setBackgroundColor(Color.WHITE);

            //CREATE TEXT VIEW FOR EMPTY CART
            TextView totalPriceTxt = new TextView(this);
            totalPriceTxt.setText(getResources().getText(R.string.cart_total)+" $"+getTotalPrice());
            RelativeLayout.LayoutParams totalPriceTxtParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            totalPriceTxtParams.setMargins(0, 0, 0, 0);
            totalPriceTxtParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            totalPriceTxtParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            totalPriceTxt.setLayoutParams(totalPriceTxtParams);
            totalPriceTxt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Regular.ttf"));
            totalPriceTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            totalPriceTxt.setTextColor(Color.BLACK);
            totalPriceTxt.setId(View.generateViewId());

            //ADD TEXT VIEW TO RELATIVE LAYOUT
            totalPriceLayout.addView(totalPriceTxt);

            //ADD RELATIVE LAYOUT TO MAIN VIEW (cartContainer)
            cartContainer.addView(totalPriceLayout);
            /*
            END TOTAL PRICE VIEW
             */


            tmp_checkout_btn = (Button) findViewById(R.id.tmp_button_checkout);
            tmp_checkout_btn.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf"));

            tmp_checkout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Cart.this, Checkout.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            });
        }
    }

    /**
     * @param i, int from DataHolder
     * @return RelativeLayout
     *
     * Generates cart items
     */
    public RelativeLayout cartItem(int i){
        RelativeLayout rl = new RelativeLayout(this);

        TextView title = null;
        TextView price = null;
        ImageView img = null;


        for (int j = 0; j < DataHolder.getInstance().getProduct(i).length; j++){
            switch(j){
                case 0://title
                    title = new TextView(this);
                    title.setText(DataHolder.getInstance().getProduct(i)[j]);
                    RelativeLayout.LayoutParams txtParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    txtParams.setMargins(pxFromDp(this, 75), 0, 0, 0);
                    txtParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                    txtParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                    title.setLayoutParams(txtParams);
                    title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
                    title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
                    title.setTextColor(Color.BLACK);
                    title.setId(View.generateViewId());

                    rl.addView(title);
                    break;

                case 3://price
                    setTotalPrice(Integer.parseInt(DataHolder.getInstance().getProduct(i)[j]));

                    price = new TextView(this);
                    price.setText("$ "+DataHolder.getInstance().getProduct(i)[j]);
                    RelativeLayout.LayoutParams priceParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    priceParams.setMargins(pxFromDp(this, 100), pxFromDp(this, 10), 0, 0);

                    if(title != null){
                        priceParams.addRule(RelativeLayout.BELOW, title.getId());
                    }

                    price.setLayoutParams(priceParams);
                    price.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
                    price.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                    price.setTextColor(Color.BLACK);
                    price.setId(View.generateViewId());

                    rl.addView(price);
                    break;

                case 4://image
                    img = new ImageView(this);
                    int drawableId = this.getResources().getIdentifier(DataHolder.getInstance().getProduct(i)[j], "drawable", this.getPackageName());
                    img.setImageResource(drawableId);
                    img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    img.setId(View.generateViewId());

                    RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    imgParams.width = pxFromDp(this, 75);
                    imgParams.height = pxFromDp(this, 75);
                    imgParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                    imgParams.setMarginStart(10);

                    img.setLayoutParams(imgParams);

                    rl.addView(img);
                    break;
                default:
                    //
                    break;
            }
            //ADD GARBAGE IMAGE + DELETE VIEW AND PRODUCT FROM CART
            ImageView trash = new ImageView(this);
            int drawableId = this.getResources().getIdentifier("trash", "drawable", this.getPackageName());
            trash.setImageResource(drawableId);
            trash.setScaleType(ImageView.ScaleType.FIT_CENTER);
            trash.setId(View.generateViewId());

            RelativeLayout.LayoutParams trashParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            trashParams.width = pxFromDp(this, 30);
            trashParams.setMargins(0,0,pxFromDp(this, 10),0);
            trashParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            trashParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

            trash.setLayoutParams(trashParams);

            final int toRemove = i;

            trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), DataHolder.getInstance().getProduct(toRemove)[0]+" has been removed from your cart.", Toast.LENGTH_LONG).show();
                    DataHolder.getInstance().removeFromCart(toRemove);
                    Intent intent = getIntent();
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            });

            rl.addView(trash);
        }

        //Add total price TextView
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rlp.setMargins(pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20));
        rl.setLayoutParams(rlp);
        rl.setPadding(10,10,10,10);
        rl.setBackgroundColor(Color.WHITE);
        return rl;
    }


    /**
     * HANDLER
     */
    View.OnClickListener handler = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if (v == slidermenu_hats){
                Intent i = new Intent(getApplicationContext() , Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "hats");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
            else if (v == slidermenu_shoes){
                Intent i = new Intent(getApplicationContext() , Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "shoes");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
            else if (v == slidermenu_glasses){
                Intent i = new Intent(getApplicationContext(), Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "glasses");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
            else if(v == slidermenu_bags){
                Intent i = new Intent(getApplicationContext(), Category.class);
                Bundle b = new Bundle();
                b.putString("cat", "bags");
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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
   GET/SET
    */

    /**
     * Get cart grand total
     * @return
     */
    private int getTotalPrice(){ return this.totalPrice; }

    /**
     * Set cart grand total by adding prices together
     * @param price
     */
    private void setTotalPrice(int price){ this.totalPrice = this.totalPrice + price; }
}
