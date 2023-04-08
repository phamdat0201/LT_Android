package com.example.bai19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String[] mValues;

    public MyAdapter(Context context, String[] values) {
        super(context, R.layout.item_listview, values);
        mContext = context;
        mValues = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_listview, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
        TextView textView = (TextView) rowView.findViewById(R.id.text);
        if (mValues[position].length() <= 3) {
            imageView.setImageResource(R.drawable.star);
        } else {
            imageView.setImageResource(R.drawable.earth);
        }
        textView.setText(mValues[position]);
        return rowView;
    }
}
