package com.coretronic.cics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class USBPnPReceiver extends BroadcastReceiver {
    static final String TAG = USBPnPReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive, action="+intent.getAction());
    }
}