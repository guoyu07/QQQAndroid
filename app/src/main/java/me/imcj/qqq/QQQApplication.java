package me.imcj.qqq;

import android.app.Application;
import android.content.Intent;

import io.sentry.Sentry;

/**
 * Created by cj on 2017/10/4.
 */

public class QQQApplication extends Application {
    private static QQQApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        Thread.setDefaultUncaughtExceptionHandler(restartHandler);
    }

    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {

        public void uncaughtException(Thread thread, Throwable ex) {
            Sentry.capture(ex);
            restartApp();
        }
    };

    public void restartApp() {
        Intent intent = new Intent(instance,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        instance.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
