package com.kpinfotech.progressnotification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	Activity activity;
	
	Button btnclick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}
	
	private void init() {
		activity = (Activity) MainActivity.this;
		
		btnclick = (Button) findViewById(R.id.btnclick);
		btnclick.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.btnclick:
			new Downloader(activity).execute();
			break;
			
		default:
			break;
        }
		
	}

}