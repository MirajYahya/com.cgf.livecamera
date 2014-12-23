package com.cgf.livecamera.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;

public class WaitDialog implements Runnable/* , OnKeyListener will be delete */{
	private static ProgressDialog waitDialog = null;
	private boolean connectWait = false;
	private Context context = null;
	public WaitDialog(Context context){
		this.context = context;
	}

//Will be delete.
//    @Override
//    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//        if (KeyEvent.KEYCODE_SEARCH == keyCode || KeyEvent.KEYCODE_BACK == keyCode) {
//            return true;
//        }
//        return false;
//    }
    
	public void startDialog(String message){
		if(this.connectWait ==  false){
			this.connectWait = true;
			setWait(message);
		}
	}

	public void stopDialog(){
		if(this.connectWait ==  true){
			this.connectWait = false;
		}
	}
	
	@Override
    public void run() {
    	while(this.connectWait){
	        try {
	            Thread.sleep(200);
	        } catch (InterruptedException e) {
	        }
    	}
        handler.sendEmptyMessage(0);
    }
     
    private Handler handler = new Handler() {
        public void handleMessage(Message msg){
        	Log.i("DEBUG","dialog dismiss");
            waitDialog.dismiss();
            waitDialog = null;
        }
    };
    
    private void setWait(String s){
        Thread thread;
    	
        waitDialog = new ProgressDialog(this.context);
        
        waitDialog.setTitle("1Hit1Error");

        waitDialog.setMessage(s);

        waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        waitDialog.setCancelable(false);
        
        waitDialog.show();
     
        thread = new Thread(this);

        thread.start();
    }
}
