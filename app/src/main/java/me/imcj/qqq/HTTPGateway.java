package me.imcj.qqq;

import android.util.Log;

import io.sentry.Sentry;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by cj on 2017/5/27.
 */

public class HTTPGateway {
    private String apiURL;

    public HTTPGateway(final String apiURL)
    {
        this.apiURL = apiURL;
    }

    public void message(final String sender, final String message)
    {
        Log.i(this.getClass().getName(), "Will start new thread");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(this.getClass().getName(), "Did start thread");
                    OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("sender", sender)
                            .addFormDataPart("message", message)
                            .build();

                    Request request = new Request.Builder()
                            .url(apiURL)
                            .method("POST", RequestBody.create(null, new byte[0]))
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    Log.i(this.getClass().getName(), "Will receive response of http request");
                    String body = response.body().string();
                    Log.i(this.getClass().getName(), "Did receive response of http request");


                } catch (Exception e) {
                    Log.e(this.getClass().getName(), e.getMessage());
                    Sentry.capture(e);
                }
            }
        }).start();

    }
}
