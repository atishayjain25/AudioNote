package com.android.audionote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		
		if(null == bundle)
			return;
		
		Log.i("IncomingCallReceiver",bundle.toString());
		String state = bundle.getString(TelephonyManager.EXTRA_STATE);
		Log.i("IncomingCallReceiver","State: "+ state);
		
		if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING))
		{
			String phonenumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
			Log.i("IncomingCallReceiver","Incomng Number: " + phonenumber);
			ListenerService.setOtherPartyPhoneNumber(phonenumber);
		}
		
		if(state.equals(TelephonyManager.EXTRA_STATE_IDLE))
		{
			try{
				  Thread.currentThread().sleep(2000);
				}
				catch(Exception ie){
				}
			ListenerService.getCallLogData(context);
		}
	}

}
