package com.detect.petsar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    private int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();

        ImageView image = (ImageView) findViewById(R.id.product_img);
        TextView title = (TextView) findViewById(R.id.product_title);
        TextView price = (TextView) findViewById(R.id.product_price);
        TextView info = (TextView) findViewById(R.id.product_info);

        img = Integer.parseInt(intent.getStringExtra("img"));
        image.setImageResource(img);

        title.setText(intent.getStringExtra("title"));
        price.setText(intent.getStringExtra("price"));
    }

    public void onClick(View view){
        if(view.getId() == R.id.btnchange){
            Intent intent01 = new Intent(getApplicationContext(), PetArActivity.class);
            startActivity(intent01);
        }

    }
}