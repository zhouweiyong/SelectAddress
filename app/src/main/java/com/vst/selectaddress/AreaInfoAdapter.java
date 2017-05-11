package com.vst.selectaddress;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vst.selectaddress.bean.AreaInfo;

import java.util.List;


/**
 * @description：
 * @author zhoukl
 * @date 2014年12月19日 下午7:03:20
 */
public class AreaInfoAdapter extends BaseAdapter {
	private Context context;
	private List<AreaInfo> mAreaInfo;
	private boolean isLast;

	public AreaInfoAdapter(Context context, List<AreaInfo> mAreaInfo, boolean isLast) {
		this.context = context;
		this.mAreaInfo = mAreaInfo;
		this.isLast = isLast;
	}
	
	public void setData(List<AreaInfo> areaInfo) {
		this.mAreaInfo = areaInfo;
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAreaInfo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mAreaInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.layout_area_info_item, null);
			holder.addressName = (TextView) convertView.findViewById(R.id.eshop_address_item_tv);
			holder.arrowIv = (ImageView) convertView.findViewById(R.id.eshop_address_item_iv);
			if (isLast) {
				holder.arrowIv.setVisibility(View.GONE);
			} else {
				holder.arrowIv.setVisibility(View.VISIBLE);
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (null != mAreaInfo) {
			holder.addressName.setText(mAreaInfo.get(position).getAreaName());
		}
		return convertView;
	}

	/**
	 * 实现listView的item重用， 对listView优化的操作
	 * 
	 * @description：
	 * @author ldm
	 * @date 2014年10月11日 下午4:56:00
	 */
	private static class ViewHolder {
		/** 分类名称 */
		private TextView addressName;
		/* 箭头图标* */
		private ImageView arrowIv;
	}
}

