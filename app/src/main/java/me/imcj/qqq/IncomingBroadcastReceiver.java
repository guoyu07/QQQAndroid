package me.imcj.qqq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.sentry.Sentry;

/**
 * Created by cj on 2017/5/24.
 */

public class IncomingBroadcastReceiver extends BroadcastReceiver {

    HTTPGateway gateway;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (gateway == null) {
            gateway = new HTTPGateway(Config.shared().apiURL());
        }

        final Bundle bundle = intent.getExtras();
        try {
            if (null == bundle)
                return;
            final Object[] objects = (Object[]) bundle.get("pdus");
            for (int i = 0, size = objects.length; i < size; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) objects[i]);
                String phoneNumber = smsMessage.getDisplayOriginatingAddress();
                String body = smsMessage.getMessageBody();
                Log.i(getClass().getName(), phoneNumber + " " + body);

                gateway.message(phoneNumber, body);
            }
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
            Sentry.capture(e);
        }

        Log.i(getClass().getName(), "On receive");
    }
}
