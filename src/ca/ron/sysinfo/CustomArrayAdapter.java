package ca.ron.sysinfo;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.andcrypt.simfast.R;

public class CustomArrayAdapter extends ArrayAdapter<String> {
	public final String TAG = "CustomArrayAdapterLog";
	private final Activity context;
	private final String[] deviceInfoLabel;
	private final String[] deviceInfoData;
	public CustomArrayAdapter(Activity context, String[] deviceInfoLabel, String[] deviceInfoData) {
		super(context, R.layout.row, deviceInfoLabel);
		this.context = context;
		this.deviceInfoLabel = deviceInfoLabel;
		this.deviceInfoData = deviceInfoData;	
		Log.w("TAG","INTO CustomArrayAdapter" + context.getPackageName());
		Log.w(TAG,deviceInfoLabel[0] + deviceInfoData[0]);
		Log.w(TAG,deviceInfoLabel[1] + deviceInfoData[1]);
		Log.w(TAG,deviceInfoLabel[2] + deviceInfoData[2]);
	}	

	@Override
	public View getView(int position, View view, ViewGroup parent) {	
		//---print the index of the row to examine---
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.row, null, true);
		
		//---get a reference to all the views on the xml layout---
		TextView txtLabel = (TextView) rowView.findViewById(R.id.txtLabel);
		TextView txtData = (TextView) rowView.findViewById(R.id.txtData);
		//---customize the content of each row based on position---
		txtLabel.setText(deviceInfoLabel[position]);
		txtData.setText(deviceInfoData[position]);
		Log.w(TAG,"deviceInfoDATA = " + deviceInfoData[position]);	
		
		return rowView;
	} 
}
