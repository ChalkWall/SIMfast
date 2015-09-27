package ca.ron.sysinfo;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.andcrypt.simfast.R;


public class Sysinfo extends ListActivity {
	private static final String  TAG = "SysinfoLog";
	
    String[] deviceInfoLabel = {
    		"Phone Number:",
    		//"Brand:",
    		"Model:",
    		"IMEI:",
    		"IMSI:",   
    		"SIM Serial Number:",
    		"Network Operator Name:",
    		"SIM Operator Name:" ,
    		"Device:",
    		"Display:",    		
    		"Hardware:", 
    		"Manufacturer:",   		
      		"Product:",
      		"Network Type:"        		
    };
	
	ListView myListView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sysinfo);
        Log.w(TAG, "INTO MAIN FUNCTION");
        
        //Build
  		String strBrand = android.os.Build.BRAND;		//The consumer-visible brand (which product/hardware). 
  		String strManufacturer = android.os.Build.MANUFACTURER;
  		String strDevice = android.os.Build.DEVICE; 	//The name of the industrial design. 
  		String strDisplay = android.os.Build.DISPLAY; 	//A build ID string meant for displaying to the user 
  		String strHardware = android.os.Build.HARDWARE;	//The name of the hardware (from the kernel command line or /proc). 
  		String strModel = android.os.Build.MODEL;		//The end-user-visible name for the end product. 
  		String strProduct = android.os.Build.PRODUCT;	//The name of the overall product. 
				
  		TelephonyManager tMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String strPhoneNum = "5555555555";

		try {
			strPhoneNum = tMgr.getLine1Number();
		}
		catch(NullPointerException ex) {
		}
		if(strPhoneNum.equals("")){
			strPhoneNum = tMgr.getSubscriberId();
		}
		 	
        //Telephony
  		//String strPhoneNum = tMgr.getLine1Number();			//Phone num.
  		String strIMEI = tMgr.getDeviceId();					//IMEI num.
  		String strIMSI = tMgr.getSubscriberId();				//IMSI num.
  		//String strLocPhone = tMgr.getCellLocation();			//Curent location of phone	
  		//String strCelTower = tMgr.getNeighboringCellInfo(); 	//Used cel. tower
  		String strNetOp = tMgr.getNetworkOperatorName();		//Used network operator
  		String strSIMOp = tMgr.getSimOperatorName();			//SIM's operator
  		String strSIMSerNum = tMgr.getSimSerialNumber();		//SIM serial num.
  		String strNetWorkType = "Unknown Network Type";			//NETWORK_TYPE_EDGE: CDMA EDGE, 2G, 3G, 4G
  		//int intNetWorkType = tMgr.getNetworkType();				//NETWORK_TYPE_EDGE: CDMA EDGE, 2G, 3G, 4G
  		      
        if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_EDGE ) {
            //  Network type is 2G
            //Log.w(TAG, "2G or GSM");
            strNetWorkType = "NETWORK_TYPE_EDGE";            
        }
        else if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_CDMA) {
            // Network type is 2G
            //Log.w(TAG, "2G or CDMA");
            strNetWorkType = "NETWORK_TYPE_CDMA";
        }
        else if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS) {
            // Network type is 3G
            //Log.w(TAG, "3G Network available.");
            strNetWorkType = "NETWORK_TYPE_UMTS";
        }
        else if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSDPA) {
            // Network type is 3G
            //Log.w(TAG, "3G Network available.");
            strNetWorkType = "NETWORK_TYPE_HSDPA";
        }
        else if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_GPRS) {
            // Network type is 3G
            //Log.w(TAG, "3G Network available.");
            strNetWorkType = "NETWORK_TYPE_GPRS";
        }
        else if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_LTE) {
            // Network type is 3G
            //Log.w(TAG, "3G Network available.");
            strNetWorkType = "NETWORK_TYPE_LTE";
        }
        else if(tMgr.getNetworkType() == TelephonyManager.PHONE_TYPE_GSM) {
            // Network type is 3G
            //Log.w(TAG, "3G Network available.");
            strNetWorkType = "PHONE_TYPE_GSM";
        }
        else if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_EHRPD) {
            // Network type is 3G
            //Log.w(TAG, "3G Network available.");
            strNetWorkType = "NETWORK_TYPE_EHRPD";
        }
        else if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPA) {
            // Network type is 3G
            //Log.w(TAG, "3G Network available.");
            strNetWorkType = "NETWORK_TYPE_HSPA";
        }
        else if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPAP) {
            // Network type is 3G
            //Log.w(TAG, "3G Network available.");
            strNetWorkType = "NETWORK_TYPE_HSPAP";
        }
        else if(tMgr.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSUPA) {
            // Network type is 3G
            //Log.w(TAG, "3G Network available.");
            strNetWorkType = "NETWORK_TYPE_HSUPA";
        }
        
        final String[] deviceInfoData = {
        		strPhoneNum,        		
        		strBrand.toUpperCase() + "  " + strModel,
        		strIMEI,
        		strIMSI,
        		strSIMSerNum,
        		strNetOp,
        		strSIMOp,        		
        		strDevice,
        		strDisplay,
        		strHardware,
        		strManufacturer,        		
        		strProduct,
        		strNetWorkType + ", " + String.valueOf(tMgr.getNetworkType())
        };
       
        //---using custom array adapter---
      Log.w(TAG,"Calling CustomArrayAdapter from " + Sysinfo.this.getPackageName());
      CustomArrayAdapter adapter = new CustomArrayAdapter(this, deviceInfoLabel, deviceInfoData);
      setListAdapter(adapter);
        
      /*
  		final String strSysInfo =
  				"Phone Number: " + strPhoneNum + "\r\n"
  				+ "IMEI: " + strIMEI + "\r\n"
  				+ "IMSI: " + strIMSI + "\r\n"
  				+ "Network Operator Name: " + strNetOp + "\r\n"
  				+ "SIM Operator Name: " + strSIMOp + "\r\n"
  				+ "SIM Serial Number: " + strSIMSerNum + "\r\n" + "\r\n"
  				+ "Brand: " + strBrand + "\r\n"
  				+ "Manufacturer: " + strManufacturer + "\r\n"
  				+ "Device: " + strDevice + "\r\n"
  				+ "Display: " + strDisplay + "\r\n"
  				+ "Hardware: " + strHardware + "\r\n"
  				+ "Model: " + strModel + "\r\n"
  				+ "Product: " + strProduct + "\r\n"
  				+ "Network Type: " + strNetWorkType + ", " + String.valueOf(tMgr.getNetworkType()) + "\r\n";
		final String strOut = strSysInfo ;
       	textView2.setText(strOut);
      */ 
       
    }
    
    // Settings menu is not mandatory in this build		;Ronny 2015-09-06
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sysinfo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_help) {
			String mTitle = getResources().getString(R.string.app_name);
			String mMessage= getResources().getString(R.string.app_description);
			MyMessageBox_OK.main(this, mTitle, mMessage);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
}