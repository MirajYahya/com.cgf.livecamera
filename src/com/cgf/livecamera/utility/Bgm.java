package com.cgf.livecamera.utility;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.cgf.onehitoneerror.R;

public class Bgm implements OnCompletionListener{
	private static Bgm instance = null;
	private static MediaPlayer mp;

	private Bgm(Context context){
		mp = MediaPlayer.create(context, R.raw.coroy);
		mp.setOnCompletionListener(this);
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		this.start();
	}

	public static Bgm getInstance(Context context){
		if(instance == null)
			instance = new Bgm(context);
		return instance;
	}
	
	public void start(){
		if(!(mp.isPlaying()))
			mp.start();
	}
	
	public void resetStart(){
		if(!(mp.isPlaying())){
			mp.seekTo(0);
			mp.start();
		}
	}
	
	public void stop(){
		mp.stop();
	}
	
	public void pause(){
		mp.pause();
	}
}
