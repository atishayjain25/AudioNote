package com.android.audionote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class OutgoingCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		
		if(null == bundle)
			return;
		
		String phonenumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
		Log.i("OutgoingCallReceiver",phonenumber);
		Log.i("OutgoingCallReceiver",bundle.toString());
		ListenerService.setOtherPartyPhoneNumber(phonenumber);
		/*if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_IDLE)
                || intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                        TelephonyManager.EXTRA_STATE_OFFHOOK)) {
			try{
				  Thread.currentThread().sleep(2000);
				}
				catch(Exception ie){
				}
			ListenerService.getCallLogData(context);
        }*/
	}
}
