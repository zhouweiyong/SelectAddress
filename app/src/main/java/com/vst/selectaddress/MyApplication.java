package com.vst.selectaddress;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.vst.selectaddress.db.AreaInfoDaoMaster;

public class MyApplication extends Application{
	private static MyApplication instance;
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
	
	public static MyApplication getInstance() {
		return instance;
	}
	public static AreaInfoDaoMaster mAreaInfoDaoMaster;
	public static AreaInfoDaoMaster getAreaInfoDaoMaster() {
		if(mAreaInfoDaoMaster == null){
			synchronized (MyApplication.class) {
				if(mAreaInfoDaoMaster == null){
					String path = AreaInfoDaoMaster.createOrUpdate(getInstance());
					SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
					mAreaInfoDaoMaster = new AreaInfoDaoMaster(db);
				}
			}
		}
		return mAreaInfoDaoMaster;
	};
}
