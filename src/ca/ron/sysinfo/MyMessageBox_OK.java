package ca.ron.sysinfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class MyMessageBox_OK {
	public final static String TAG = "MyMessageBox_OKLog: ";
	static void main(final Context context, String mTitle, String mMessage){
		Log.w (TAG, "Into Message Box...");
		AlertDialog.Builder builder = new AlertDialog.Builder(context, 2);		
		builder.setMessage(mMessage)
			.setIcon(android.R.drawable.ic_dialog_alert).setTitle(mTitle)
			.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
			public void onClick(DialogInterface dialog, int id) {
				Log.w (TAG, "onClick(DialogInterface dialog, int id) ...");
				/*
				dialog.cancel(); 	
				Intent intent = new Intent(Intent.ACTION_MAIN);
	    		intent.addCategory(Intent.CATEGORY_HOME);
	    		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		context.startActivity(intent);
	    		*/
			}
	       });
		AlertDialog alert = builder.create();
		alert.show();
		Log.w (TAG, "Before exiting MyMessageBox_OK function ...");
	}	
}
