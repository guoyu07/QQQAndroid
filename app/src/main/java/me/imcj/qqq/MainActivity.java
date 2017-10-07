package me.imcj.qqq;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.sentry.Sentry;
import io.sentry.android.AndroidSentryClientFactory;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context ctx = this.getApplicationContext();
        // Use the Sentry DSN (client key) from the Project Settings page on Sentry
        Config.initialize(getApplicationContext());
        Config config = Config.shared();
        String sentryDsn = config.sentry();

        Sentry.init(sentryDsn, new AndroidSentryClientFactory(ctx));

        Intent intent = new Intent(this, IncomingService.class);
        startService(intent);

        Log.i(getClass().getName(), "Activity created]");
    }



}
