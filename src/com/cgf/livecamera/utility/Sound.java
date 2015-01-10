package com.cgf.livecamera.utility;

import com.cgf.livecamera.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sound {
	private static Sound instance = null;
	private static SoundPool sp;
	private static int id1;
	private static int id2;
	
	private Sound(Context context){
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
//		id1 = sp.load(context, R.raw.button001, 1);
//		id2 = sp.load(context, R.raw.button002, 1);
	}
	
	public static Sound getInstance(Context context){
		if(instance == null)
			instance = new Sound(context);
		return instance;
	}
	
	public void start(int id){
		switch(id){
		case 1 : sp.play(id1, 1.0F, 1.0F, 0, 0, 1.0F); break;
		case 2 : sp.play(id2, 1.0F, 1.0F, 0, 0, 1.0F); break;
		}
	}
}
