package me.imcj.qqq;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by cj on 2017/5/24.
 */

public class IncomingService extends Service {
    public void IncomingService() {
        int here = 1;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getClass().getName(), "Service on create");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
