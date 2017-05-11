package com.vst.selectaddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.vst.selectaddress.bean.AreaInfo;
import com.vst.selectaddress.db.AreaInfoDbHelper;

import java.util.ArrayList;
import java.util.List;


public class ChoiceProvienceActivity extends Activity implements
		OnItemClickListener {
	private ListView listView;
	private AreaInfoAdapter mAddressAdapter;
	private List<AreaInfo> provinceList = new ArrayList<AreaInfo>();
	private ImageButton mBackBtn;
	private AreaInfoDbHelper mAreaInfoDbHelper;
	private int mLevel = 1;
	private AreaInfo mProvienceInfo, mCityInfo, mAreaInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_choice_area);
		listView = (ListView) findViewById(R.id.eshop_select_lv);
		mAddressAdapter = new AreaInfoAdapter(this, provinceList, false);
		listView.setAdapter(mAddressAdapter);
		listView.setOnItemClickListener(this);
		mAreaInfoDbHelper = new AreaInfoDbHelper();
		initDatas();
	}
	
	protected boolean initDatas() {
		switch (mLevel) {
			case 1:
				provinceList = mAreaInfoDbHelper.getProvinceList();
				break;
			case 2:
				provinceList = mAreaInfoDbHelper.getAreaListByParentID(mProvienceInfo.getAreaId());
				break;
			case 3:
				provinceList = mAreaInfoDbHelper.getAreaListByParentID(mCityInfo.getAreaId());
				break;
		}
		
		if(mLevel>3 || provinceList==null || provinceList.size()==0){
			return false;
		}
		
		mAddressAdapter.setData(provinceList);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (parent instanceof ListView) {
			int realPosition = position - ((ListView) parent).getHeaderViewsCount();
			if (realPosition >= 0 && realPosition < mAddressAdapter.getCount()) {
				if (mLevel == 1) {
					mProvienceInfo = provinceList.get(realPosition);
				} else if(mLevel == 2){
					mCityInfo = provinceList.get(realPosition);
				}
				mLevel++;
				if(!initDatas()){//如果没有下一级，则终止
					String address = mProvienceInfo.getAreaName() + " " + mCityInfo.getAreaName();
					int areaId = mCityInfo.getAreaId();
					if(provinceList!=null && provinceList.size()>0){
						mAreaInfo = provinceList.get(realPosition);
						address = address + " " + mAreaInfo.getAreaName();
						areaId = mAreaInfo.getAreaId();
					}
					// 地区选择完毕， 返回调用界面
					Bundle bundle = new Bundle();
					bundle.putString("address", address); // 中文地址
					bundle.putInt("areaId", areaId); // 地区ID
					Intent mIntent = getIntent();
					mIntent.putExtras(bundle);
					setResult(Activity.RESULT_OK, mIntent);
					finish();
				}
			}
		}
	}
}
