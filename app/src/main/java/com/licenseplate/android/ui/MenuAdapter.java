package com.licenseplate.android.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.licenseplate.android.R;

public class MenuAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mMenuArray;

    public MenuAdapter(Context context, String[] menuArray) {
        this.mContext = context;
        this.mMenuArray = menuArray;
    }

    @Override
    public int getCount() {
        return mMenuArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mMenuArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menu_item,
                    parent, false);
            holder = new ViewHolder();
            holder.label = (TypefacedTextView) convertView.findViewById(R.id.label);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.label.setText(mMenuArray[position]);
        return convertView;
    }

    class ViewHolder {
        TypefacedTextView label;
    }
}
