package me.imcj.qqq;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.sentry.Sentry;

/**
 * Created by cj on 2017/10/7.
 */

public class Config {
    private static Config shared;

    private String apiURL;
    private String sentry;

    public static void initialize(Context context) {
        Properties properties;
        shared = new Config();

        try {
            InputStream stream = context.getAssets().open("config.properties");
            properties = new Properties();
            properties.load(stream);
            shared.setApiURL(properties.getProperty("apiURL"));
            shared.setSentry(properties.getProperty("sentry"));

        } catch (IOException error) {
            Sentry.capture(error);
        }
    }

    public static Config shared() {
        return shared;
    }

    public String apiURL() {
        return apiURL;
    }

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }

    public String sentry() {
        return sentry;
    }

    public void setSentry(String sentry) {
        this.sentry = sentry;
    }
}
