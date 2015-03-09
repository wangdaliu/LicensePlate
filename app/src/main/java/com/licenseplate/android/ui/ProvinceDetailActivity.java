package com.licenseplate.android.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.licenseplate.android.MyApplication;
import com.licenseplate.android.R;
import com.licenseplate.android.objects.Plate;

import java.util.ArrayList;
import java.util.List;

public class ProvinceDetailActivity extends BaseActivity {

    private PlateAdapter mPlateAdapter;

    private List<Plate> mPlateList = new ArrayList<Plate>();

    private ListView mListView;

    private String mProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.province_detail);
        mProvince = getIntent().getStringExtra("province");
        mPlateList = filterPlate(mProvince);
        mPlateAdapter = new PlateAdapter(mPlateList, this);
        mListView = (ListView) findViewById(R.id.plate_list);
        mListView.setAdapter(mPlateAdapter);
    }

    private List<Plate> filterPlate(String province) {
        List<Plate> tempPlateList = new ArrayList<Plate>();
        List<Plate> plates = MyApplication.getInstance().getPlateList();
        for (Plate plate : plates) {
            if (plate.getProvince().equals(province)) {
                tempPlateList.add(plate);
            }
        }
        return tempPlateList;
    }

    private class PlateAdapter extends BaseAdapter {

        private List<Plate> mPlateList;

        private Context mContext;

        private PlateAdapter(List<Plate> plateList, Context context) {
            this.mPlateList = plateList;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mPlateList.size();
        }

        @Override
        public Object getItem(int position) {
            return mPlateList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.plate_item, null);
                holder.tvCity = (TextView) convertView.findViewById(R.id.city);
                holder.tvPlate = (TextView) convertView.findViewById(R.id.plate);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvCity.setText(mPlateList.get(position).getLocation());
            holder.tvPlate.setText(mPlateList.get(position).getBrand());
            return convertView;
        }
    }


    class ViewHolder {
        public TextView tvCity;
        public TextView tvPlate;
    }
}
