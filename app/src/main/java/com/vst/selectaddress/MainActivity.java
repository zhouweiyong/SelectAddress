package com.vst.selectaddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	private TextView tvAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvAdd = (TextView) findViewById(R.id.tv_add);
		tvAdd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this,
				ChoiceProvienceActivity.class);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == 1) {
				Bundle bundle = data.getExtras();
				String address = bundle.getString("address");
				tvAdd.setText(address);
			}
		}
	}
}
