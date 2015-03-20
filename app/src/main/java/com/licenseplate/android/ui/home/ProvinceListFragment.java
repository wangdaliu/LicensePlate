package com.licenseplate.android.ui.home;

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
import com.licenseplate.android.ui.BaseFragment;
import com.licenseplate.android.util.PlateUtil;


public class ProvinceListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ProvinceAdapter mProvinceAdapter;

    private String[] mProvinceList;

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
        intent.putExtra("province", mProvinceList[position]);
        startActivity(intent);
    }

    private class ProvinceAdapter extends BaseAdapter {

        private String[] mProvinceList;

        private Context mContext;

        private ProvinceAdapter(String[] provinceList, Context context) {
            this.mProvinceList = provinceList;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mProvinceList.length;
        }

        @Override
        public Object getItem(int position) {
            return mProvinceList[position];
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

            holder.tvProvince.setText(mProvinceList[position]);
            return convertView;
        }
    }


    class ViewHolder {
        public TextView tvProvince;
    }
}
