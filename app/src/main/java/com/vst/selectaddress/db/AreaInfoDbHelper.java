package com.vst.selectaddress.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.vst.selectaddress.MyApplication;
import com.vst.selectaddress.bean.AreaInfo;

import java.util.List;


public class AreaInfoDbHelper {
	
	private AreaInfoDaoSession mAreaInfoDaoSession;  
    private AreaInfoDao mAreaInfoDao;  
	private SQLiteDatabase db;
	private Context mContext;
    public AreaInfoDbHelper() {  
    	AreaInfoDaoMaster daoMaster = MyApplication.getAreaInfoDaoMaster();
    	mAreaInfoDaoSession=daoMaster.newSession(); 
    	mAreaInfoDao = mAreaInfoDaoSession.getAreaInfoDao();
		db = daoMaster.getDatabase();
    }
    
    public AreaInfoDbHelper(Context mContext) {  
    	this();
    	this.mContext=mContext;
    }
    
	public long getTotalCount(){
		return mAreaInfoDao.queryBuilder().count();
	}
	
	public List<AreaInfo> getProvinceList() {
		return mAreaInfoDao.queryBuilder().where(AreaInfoDao.Properties.ParentID.eq(1)).orderAsc(AreaInfoDao.Properties.AreaID).list();
	}
	

	public List<AreaInfo> getAreaListByParentID(int parentID) {
		return mAreaInfoDao.queryBuilder().where(AreaInfoDao.Properties.ParentID.eq(parentID)).orderAsc(AreaInfoDao.Properties.AreaID).list();
	}
	
	
	
}

