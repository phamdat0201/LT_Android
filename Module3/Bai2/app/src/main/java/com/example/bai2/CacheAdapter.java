package com.example.bai2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CacheAdapter extends ArrayAdapter<Cache> {

    private Context context;
    private int resource;

    public CacheAdapter(Context context, int resource, List<Cache> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Cache cache = getItem(position);

        TextView cacheView = convertView.findViewById(R.id.cache_title);
        cacheView.setText(cache.getTitle());

        return convertView;
    }
}
