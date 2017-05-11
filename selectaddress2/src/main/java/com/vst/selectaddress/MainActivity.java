package com.vst.selectaddress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        showToast("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            showToast(province.getAreaName() + city.getAreaName());
                        } else {
                            showToast(province.getAreaName() + city.getAreaName() + county.getAreaName());
                        }
                    }
                });
                task.execute("贵州", "毕节", "纳雍");
                break;
        }
    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
