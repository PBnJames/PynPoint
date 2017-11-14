package com.example.android.pynpoint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ShopAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<ShopItem> mDataSource;


    public ShopAdapter(Context context, ArrayList<ShopItem> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_item_shop, parent, false);
        // Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.shop_list_title);

        // Get subtitle element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.shop_list_subtitle);

        // Get detail element
        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.shop_list_detail);

        // Get thumbnail element
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(R.id.shop_list_thumbnail);

        ShopItem shopItem = (ShopItem) getItem(i);

        titleTextView.setText(shopItem.title);
        subtitleTextView.setText(shopItem.description);
        detailTextView.setText(shopItem.label);
        thumbnailImageView.setImageDrawable(shopItem.image);

        return rowView;
    }
}
