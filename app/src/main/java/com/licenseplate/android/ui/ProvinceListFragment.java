package com.licenseplate.android.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.licenseplate.android.R;
import com.licenseplate.android.objects.Province;
import com.licenseplate.android.util.PlateUtil;

import java.util.ArrayList;
import java.util.List;

public class ProvinceListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ProvinceAdapter mProvinceAdapter;

    private List<Province> mProvinceList = new ArrayList<Province>();

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.province, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProvinceList = PlateUtil.convertProvince();
        mProvinceAdapter = new ProvinceAdapter(mProvinceList, getActivity());
        mListView = (ListView) view.findViewById(R.id.list);
        mListView.setAdapter(mProvinceAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ProvinceDetailActivity.class);
        intent.putExtra("province", mProvinceList.get(position).getProvince());
        startActivity(intent);
    }

    private class ProvinceAdapter extends BaseAdapter {

        private List<Province> mProvinceList;

        private Context mContext;

        private ProvinceAdapter(List<Province> provinceList, Context context) {
            this.mProvinceList = provinceList;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mProvinceList.size();
        }

        @Override
        public Object getItem(int position) {
            return mProvinceList.get(position);
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
                convertView = LayoutInflater.from(mContext).inflate(R.layout.province_item, null);
                holder.tvProvince = (TextView) convertView.findViewById(R.id.province);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvProvince.setText(mProvinceList.get(position).getProvince());
            return convertView;
        }
    }


    class ViewHolder {
        public TextView tvProvince;
    }
}
