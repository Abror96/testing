package com.era.a100;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.era.a100.SchemeClass;

public class AndroidLauncher extends AndroidApplication implements OnClickListener {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SchemeClass(), config);

		SchemeClass.setOnClickListener(this);
	}

	@Override
	public void onRackClicked(String position) {
		Log.d("LOGGERR", "onRackClicked: " + position);
	}
}
