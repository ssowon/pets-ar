package com.detect.petsar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.detect.petsar.env.Logger;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final Logger LOGGER = new Logger();
    private ArrayList<ProductItem> data = null; // 목록
    private String pet_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        try {
            pet_type = intent.getExtras().getString("pet_type");
            LOGGER.i("pet type : %s", pet_type);
        } catch (final Exception e) {
            LOGGER.e(e, "Couldn't fonund type and kind of pet");
        }

        ListView list = (ListView) findViewById(R.id.list01);

        data = new ArrayList<>();
        ProductItem dog1 = new ProductItem(R.drawable.d1, "강아지01", "5,000원");
        ProductItem dog2 = new ProductItem(R.drawable.d2, "강아지02", "10,000원");
        ProductItem dog3 = new ProductItem(R.drawable.d3, "강아지03", "50,000원");
        ProductItem dog4 = new ProductItem(R.drawable.d4, "강아지04", "60,000원");
        ProductItem dog5 = new ProductItem(R.drawable.d5, "강아지05", "100,000원");
        ProductItem cat1 = new ProductItem(R.drawable.c1, "고양이01", "7,000원");
        ProductItem cat2 = new ProductItem(R.drawable.c2, "고양이02", "10,000원");
        ProductItem cat3 = new ProductItem(R.drawable.c3, "고양이03", "8,000원");
        ProductItem cat4 = new ProductItem(R.drawable.c4, "고양이04", "25,000원");
        ProductItem cat5 = new ProductItem(R.drawable.c5, "고양이05", "15,000원");

        if(pet_type.equals("dog")) {
            data.add(dog1);
            data.add(dog2);
            data.add(dog3);
            data.add(dog4);
            data.add(dog5);
        }
        else if(pet_type.equals("cat")) {
            data.add(cat1);
            data.add(cat2);
            data.add(cat3);
            data.add(cat4);
            data.add(cat5);
        }
        else {
            data.add(dog1);
            data.add(dog2);
            data.add(dog3);
            data.add(dog4);
            data.add(dog5);
            data.add(cat1);
            data.add(cat2);
            data.add(cat3);
            data.add(cat4);
            data.add(cat5);
        }

        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item, data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);

                intent.putExtra("img", Integer.toString(data.get(position).getImg()));
                intent.putExtra("title", data.get(position).getTitle());
                intent.putExtra("price", data.get(position).getPrice());

                startActivity(intent);
            }
        });
    }

    public void onClick(View v){
    }
}