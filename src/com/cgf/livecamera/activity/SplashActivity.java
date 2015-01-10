package com.cgf.livecamera.activity;

import com.cgf.livecamera.R.id;
import com.cgf.livecamera.R.layout;
import com.cgf.livecamera.R;
import com.cgf.livecamera.utility.Adware;
import com.cgf.livecamera.utility.Bgm;
import com.cgf.livecamera.utility.Sound;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SplashActivity extends Activity implements OnClickListener{
//	private float actDownX;
//	private float actDownY;
//	private float actUpX;
//	private float actUpY;
	private ImageButton ib = null;
	private Bgm bgm;
	private Sound sound;
	private Adware ad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		this.ib = (ImageButton)findViewById(R.id.imageButton1);
		this.ib.setOnClickListener(this);

//		bgm = Bgm.getInstance(getApplicationContext());
		sound = Sound.getInstance(getApplicationContext());
	}
		
	@Override
	public void onClick(View v) {
//		sound.start(1);
		Intent intent = new Intent(SplashActivity.this, com.cgf.livecamera.MainActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onResume(){
//		this.ad = new Adware(this, (LinearLayout)findViewById(R.id.linearLayoutAd));
//		this.ad.create();
//		bgm.resetStart();
		super.onResume();
	}
	
	@Override
	public void onPause(){
//		bgm.pause();
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
//		this.ad.destory();
	}
}
