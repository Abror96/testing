package com.era.a100.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.era.a100.OnClickListener;
import com.era.a100.R;
import com.era.a100.SchemeClass;

public class AndroidLauncherActivity extends AndroidApplication implements OnClickListener {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout linearLayout = createLinearLayout();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		linearLayout.addView(initializeForView(new SchemeClass(), config));
		setContentView(linearLayout);

		SchemeClass.setOnClickListener(this);
	}

	private LinearLayout createLinearLayout() {
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.addView(createToolbar());
		return linearLayout;
	}

	private Toolbar createToolbar() {
		Toolbar toolbar = new Toolbar(this);
		toolbar.setBackgroundResource(R.color.colorPrimary);
		toolbar.setTitleTextColor(Color.WHITE);
		toolbar.setElevation(10);
		toolbar.setTitle("Схема");
		return toolbar;
	}

	@Override
	public void onRackClicked(String position) {
		Log.d("LOGGERR", "onRackClicked: " + position);

		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("text", ""+position);
		startActivity(intent);
	}
}
