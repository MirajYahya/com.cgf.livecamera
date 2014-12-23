package com.cgf.livecamera.utility;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class MessageDialog {
	public static void show(Context context,String title, String message){
	    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
	
	    alertDialogBuilder.setTitle(title);
	
	    alertDialogBuilder.setMessage(message);
	
	    alertDialogBuilder.setPositiveButton("OK",
	            new DialogInterface.OnClickListener() {
	                @Override
	                public void onClick(DialogInterface dialog, int which) {
	                }
	            });
	
	    alertDialogBuilder.setCancelable(true);
	    AlertDialog alertDialog = alertDialogBuilder.create();
	
	    alertDialog.show();
	}
}
