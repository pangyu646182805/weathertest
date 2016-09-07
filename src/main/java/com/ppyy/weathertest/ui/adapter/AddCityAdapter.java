package com.ppyy.weathertest.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ppyy.weathertest.R;
import com.ppyy.weathertest.ui.bean.HeFenCityBean;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.utils.PrefUtils;
import com.ppyy.weathertest.ui.view.MyListView;
import com.ppyy.weathertest.ui.view.PinnedHeaderListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/4.
 */

public class AddCityAdapter extends BaseAdapter implements PinnedHeaderListView.PinnedHeaderAdapter, AbsListView.OnScrollListener {
    private List<HeFenCityBean.CityInfoBean> mCityInfoBeanList;
    private Context mContext;
    private int lastItem = 0;
    private List<String> provList;
    private RecyclerView mProvRecyclerView;

    public void setCityInfoBeanList(List<HeFenCityBean.CityInfoBean> cityInfoBeanList, List<String> provList) {
        mCityInfoBeanList = cityInfoBeanList;
        this.provList = provList;
        notifyDataSetChanged();
    }

    public AddCityAdapter(Context context, RecyclerView provRecyclerView) {
        mContext = context;
        this.mProvRecyclerView = provRecyclerView;
    }

    @Override
    public int getCount() {
        if (provList == null) {
            return 0;
        }
        return provList.size();
    }

    @Override
    public Object getItem(int position) {
        if (provList == null) {
            return null;
        }
        return provList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_add_city, null);
            holder = new Holder();
            holder.tvHeader = (TextView) convertView.findViewById(R.id.tv_header);
            holder.mLv = (MyListView) convertView.findViewById(R.id.my_lv);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tvHeader.setText(provList.get(position));
        Adapter adapter = new Adapter(position);
        holder.mLv.setAdapter(adapter);
        holder.mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adapter.holder h = (Adapter.holder) view.getTag();
                Intent intent = new Intent(Constants.ACTION_ADD_CITY_RESULT);
                intent.putExtra(Constants.EXTRA_CITY_NAME, h.tvContent.getText().toString());
                intent.putExtra(Constants.EXTRA_CITY_ID, (String) h.tvContent.getTag());
                mContext.sendBroadcast(intent);
            }
        });
        if (lastItem == position) {
            holder.tvHeader.setVisibility(View.INVISIBLE);
        } else {
            holder.tvHeader.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class Holder {
        TextView tvHeader;
        MyListView mLv;
    }

    private boolean isClick;

    public void setClick(boolean click) {
        isClick = click;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view instanceof PinnedHeaderListView) {
            ((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
        }
        isClick = false;
    }

    @Override
    public int getPinnedHeaderState(int position) {
        return PINNED_HEADER_PUSHED_UP;
    }

    @Override
    public void configurePinnedHeader(View header, int position) {
        if (lastItem != position) {
            notifyDataSetChanged();
        }
        ((TextView) header.findViewById(R.id.header_text)).setText(provList.get(position));
        lastItem = position;
        if (mProvRecyclerView != null) {
            int checkedPosition = PrefUtils.getInt(mContext, Constants.CHECKED_POSITION, 0);
            if (checkedPosition != position) {
                PrefUtils.setInt(mContext, Constants.CHECKED_POSITION, position);
                if (!isClick) {
                    mProvRecyclerView.getAdapter().notifyDataSetChanged();
                    mProvRecyclerView.smoothScrollToPosition(position);
                }
            }
        }
    }

    class Adapter extends BaseAdapter {
        private List<HeFenCityBean.CityInfoBean> mDataList;

        public Adapter(int position) {
            String prov = provList.get(position);
            mDataList = new ArrayList<>();
            for (HeFenCityBean.CityInfoBean bean : mCityInfoBeanList) {
                if (prov.equals(bean.getProv())) {
                    mDataList.add(bean);
                }
            }
        }

        @Override
        public int getCount() {
            if (mDataList == null) {
                return 0;
            }
            return mDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            holder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item, null);
                holder = new holder();
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(holder);
            } else {
                holder = (Adapter.holder) convertView.getTag();
            }
            holder.tvContent.setText(mDataList.get(position).getCity());
            holder.tvContent.setTag(mDataList.get(position).getId());
            return convertView;
        }

        public class holder {
            TextView tvContent;
        }
    }
}
