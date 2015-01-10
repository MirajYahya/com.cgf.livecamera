package com.cgf.livecamera.utility;

import java.io.File;

import android.content.Context;
import android.util.Log;

public class Permission {
	public static boolean setExecutable(Context context, String name){
		String path = context.getFilesDir().toString() + "/" + name;

		boolean result = false;
		
		Log.d("PPCAM","path = " + path);
		File target = new File(path);
		
		if(target.exists()){
			target.setExecutable(true);
			
			result = true;
		}
		else{
			Log.d("PPCAM","file not exists");
		}
		
		return result;
	}
}
