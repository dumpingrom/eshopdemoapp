package com.videodesk.eshopdemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
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
public class Cart extends Activity {

    Button tmp_checkout_btn = null;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        Typeface georgia = Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Regular.ttf");
        Typeface roboto_black = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Black.ttf");
        Typeface roboto_bold = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Bold.ttf");
        Typeface roboto_italic = Typeface.createFromAsset(getAssets(), "fonts/Roboto_Italic.ttf");

        LinearLayout cartContainer = (LinearLayout) findViewById(R.id.cart_container);
        TextView headerCartTitle = (TextView)findViewById(R.id.header_cart_title);
        headerCartTitle.setTypeface(roboto_bold);


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
            emptyCartTxt.setText("Your shopping cart is empty... :(");
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
            tmp_checkout_btn = (Button) findViewById(R.id.tmp_button_checkout);
            tmp_checkout_btn.setTypeface(roboto_black);

            tmp_checkout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Cart.this, Checkout.class);
                    startActivity(intent);
                }
            });
        }
    }

    public RelativeLayout cartItem(int i){
        RelativeLayout rl = new RelativeLayout(this);

        TextView title = null;
        TextView price = null;
        ImageView img = null;


        for (int j = 0; j < DataHolder.getInstance().getProduct(i).length; j++){
            //Log.d("PRODUCT ADDED*********", DataHolder.getInstance().getProduct(i)[j]);
            switch(j){
                case 0://title
                    title = new TextView(this);
                    title.setText(DataHolder.getInstance().getProduct(i)[j]);
                    RelativeLayout.LayoutParams txtParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    txtParams.setMargins(pxFromDp(this, 100), 0, 0, 0);
                    txtParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                    txtParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                    title.setLayoutParams(txtParams);
                    title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Georgia.ttf"));
                    title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
                    title.setTextColor(Color.BLACK);
                    title.setId(View.generateViewId());

                    rl.addView(title);
                    Log.d("Product Cart*********", "Adding "+DataHolder.getInstance().getProduct(i)[j]+" as View #"+title.getId());
                    break;
                case 1://short
                    /*TextView shortDesc = new TextView(this);
                    shortDesc.setText(DataHolder.getInstance().getProduct(i)[j]);
                    RelativeLayout.LayoutParams shortDescParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    shortDescParams.setMargins(pxFromDp(this, 100), pxFromDp(this, 20), 0, 0);
                    shortDescParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                    shortDesc.setLayoutParams(shortDescParams);
                    shortDesc.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto_Regular.ttf"));
                    shortDesc.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                    shortDesc.setTextColor(Color.BLACK);
                    shortDesc.setId(View.generateViewId());

                    rl.addView(shortDesc);
                    Log.d("Product Cart*********", "Adding "+DataHolder.getInstance().getProduct(i)[j]+" as View #"+shortDesc.getId());
                    break;*/
                case 2://long
                    //
                    break;
                case 3://price
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
                    Log.d("Product Cart*********", "Adding "+DataHolder.getInstance().getProduct(i)[j]+" as View #"+price.getId());
                    break;
                case 4://image
                    img = new ImageView(this);
                    int drawableId = this.getResources().getIdentifier(DataHolder.getInstance().getProduct(i)[j], "drawable", this.getPackageName());
                    img.setImageResource(drawableId);
                    img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    img.setId(View.generateViewId());

                    RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    imgParams.width = pxFromDp(this, 75);
                    imgParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                    imgParams.setMarginStart(10);

                    img.setLayoutParams(imgParams);

                    rl.addView(img);
                    Log.d("Product Cart*********", "Adding "+DataHolder.getInstance().getProduct(i)[j]+" as View #"+img.getId());
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

            RelativeLayout.LayoutParams trashParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
                    finish();
                }
            });

            rl.addView(trash);
            Log.d("Product Cart*********", "Adding trash icon as View #"+trash.getId());
        }

        Log.d("PRODUCT IN CART******", "Displaying Product "+DataHolder.getInstance().getProduct(i)[0]+" in Cart");
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.setMargins(pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20), pxFromDp(this, 20));
        rl.setLayoutParams(rlp);
        rl.setPadding(10,10,10,10);
        rl.setBackgroundColor(Color.WHITE);
        return rl;
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
}
