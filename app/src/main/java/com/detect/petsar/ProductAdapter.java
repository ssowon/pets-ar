package com.detect.petsar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ProductItem> data; // 목록 담을 배열
    private int layout;

    public ProductAdapter(Context context, int layout, ArrayList<ProductItem> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() { // product 개수
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }
        ProductItem productItem = data.get(position);

        // 제품 사진 연동
        ImageView img = (ImageView) convertView.findViewById(R.id.list_img);
        img.setImageResource(productItem.getImg());

        TextView title = (TextView) convertView.findViewById(R.id.list_title);
        title.setText(productItem.getTitle());

        TextView price = (TextView) convertView.findViewById(R.id.list_price);
        price.setText(productItem.getPrice());

        return convertView;
    }
}
